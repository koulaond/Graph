package impl.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.apache.solr.common.params.MapSolrParams;

import core.repository.GraphContainer;
import core.repository.RepositoryConnector;
import core.repository.data.DataUnit;
import core.repository.data.Error;
import core.repository.data.NodeChangeRepositoryResult;
import core.repository.data.NodeDataBucket;
import core.repository.data.RepositoryResult;
import core.repository.data.ResultStatus;
import core.repository.idprovider.IdProvider;

import static core.repository.data.ErrorCode.REPOSITORY_CHANGE;
import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;

public class SolrRepositoryConnector implements RepositoryConnector {

  public static final String NODE_ID = "_id";

  private SolrClient solrClient;
  private IdProvider idProvider;

  public SolrRepositoryConnector(SolrClient solrClient, IdProvider idProvider) {
    this.solrClient = solrClient;
    this.idProvider = idProvider;
  }

  @Override
  public NodeChangeRepositoryResult patch(NodeDataBucket dataBucket, GraphContainer graphContainer) {
    SolrInputDocument inputDocument = null;
    NodeChangeRepositoryResult result = null;
    UUID id = dataBucket.getNodeId();
    if (id == null) {
      // CASE create new node
      inputDocument = new SolrInputDocument();
      SolrInputField idField = new SolrInputField(NODE_ID);
      idField.setValue(idProvider.getNextId());
      inputDocument.addField(NODE_ID, idField);
    } else {
      // CASE update existing node
      SolrDocument foundDocument = findDocument(id);
      if (foundDocument == null) {
        result = new NodeChangeRepositoryResult(
            ResultStatus.FAILED,
            graphContainer,
            of(new Error(REPOSITORY_CHANGE, format("Cannot proceed update: Document with id=%s not found", id))).collect(toSet()), null);
      }
    }

    insertValuesToDocument(inputDocument, dataBucket);
    try {
      solrClient.add(inputDocument);
      UpdateResponse updateResponse = solrClient.commit();

    } catch (SolrServerException | IOException e) {
      result = new NodeChangeRepositoryResult(
          ResultStatus.FAILED,
          graphContainer,
          of(new Error(REPOSITORY_CHANGE, e.getMessage())).collect(toSet()),
          null
      );
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public RepositoryResult delete(Long nodeId, GraphContainer graphContainer) {
    return null;
  }

  private SolrDocument findDocument(UUID id) {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put(NODE_ID, id.toString());
    MapSolrParams mapSolrParams = new MapSolrParams(queryMap);

    SolrDocumentList results;
    try {
      QueryResponse response = solrClient.query(mapSolrParams);
      results = response.getResults();
    } catch (SolrServerException | IOException e) {
      throw new IllegalStateException(format("Unable to find document with id=%s.", id), e);
    }
    if (results.size() > 1) {
      throw new IllegalStateException(format("There is more than one document with id=%s", id));
    }
    return results == null || results.isEmpty() ? null : results.stream().findFirst().get();
  }

  private void insertValuesToDocument(SolrInputDocument solrDocument, NodeDataBucket dataBucket) {
    dataBucket.entrySet()
        .stream()
        .forEach(entry -> {
          String propertyName = entry.getKey();
          DataUnit value = entry.getValue();
          SolrInputField inputField = new SolrInputField(propertyName);
          inputField.setValue(value);
          solrDocument.put(propertyName, inputField);
        });
  }
}
