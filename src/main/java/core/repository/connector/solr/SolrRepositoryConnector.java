package core.repository.connector.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;
import org.apache.solr.common.params.MapSolrParams;

import core.repository.connector.RepositoryConnector;
import core.repository.data.DataUnit;
import core.repository.data.NodeChangeRepositoryResult;
import core.repository.data.NodeDataBucket;
import core.repository.data.RepositoryResult;
import core.repository.idprovider.IdProvider;

import static java.lang.String.format;

public class SolrRepositoryConnector implements RepositoryConnector {

  public static final String NODE_ID = "_id";

  private SolrClient solrClient;
  private IdProvider idProvider;
  private String core;

  public SolrRepositoryConnector(SolrClient solrClient, String coreName, IdProvider idProvider) {
    this.solrClient = solrClient;
    this.idProvider = idProvider;
    this.core = coreName;
  }

  @Override
  public NodeChangeRepositoryResult proceedNodeChange(NodeDataBucket dataBucket) {
    SolrInputDocument inputDocument = null;
    UUID id = dataBucket.getNodeId();
    if (id == null) {
      inputDocument = new SolrInputDocument();
      SolrInputField idField = new SolrInputField(NODE_ID);
      idField.setValue(idProvider.getNextId());
      inputDocument.addField(NODE_ID, idField);
    } else {
      SolrDocument foundDocument = findDocument(id);
      if (foundDocument == null) {
        throw new IllegalStateException(format("Document with id=%s not found", id));
      }
    }
    insertValuesToDocument(inputDocument, dataBucket);
    try {
      solrClient.add(core, inputDocument);
    } catch (SolrServerException | IOException e) {
      throw new IllegalStateException(format("Cannot change repository %s, unable to change state for node with id=%s", core, id));
    }
    return null;
  }

  @Override
  public RepositoryResult deleteNode(Long nodeId) {
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
