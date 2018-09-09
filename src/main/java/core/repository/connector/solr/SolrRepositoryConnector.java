package core.repository.connector.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
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

  public static final String NODE_ID = "nodeId";

  private SolrClient solrClient;
  private IdProvider idProvider;
  private String core;

  public SolrRepositoryConnector(SolrConfig config, IdProvider idProvider) {
    this.solrClient = new HttpSolrClient.Builder(config.getRootUrl())
        .withConnectionTimeout(config.getConnectionTimeout())
        .withSocketTimeout(config.getSocketTimeout())
        .build();
    this.idProvider = idProvider;
    this.core = config.getCore();
  }

  @Override
  public NodeChangeRepositoryResult proceedNodeChange(NodeDataBucket dataBucket) {
    Long id = dataBucket.getNodeId();
    SolrInputDocument solrDocument;
    if (id == null) {
      solrDocument = new SolrInputDocument();
      SolrInputField idField = new SolrInputField(NODE_ID);
      idField.setValue(idProvider.getNextId());
      solrDocument.addField(NODE_ID, idField);
    } else {
      SolrDocument document = findDocument(id);
      // TODO map values from solrDocument tp solrInputDocument
    }
    insertValues(solrDocument, dataBucket);
    try {
      solrClient.add(core, solrDocument);
    } catch (SolrServerException | IOException e) {
      throw new IllegalStateException(format("Cannot change repository %s, unable to change state for node with id=%s", core, id));
    }
    return null;
  }

  @Override
  public RepositoryResult deleteNode(Long nodeId) {
    return null;
  }

  private SolrDocument findDocument(Long id) {
    Map<String, String> queryMap = new HashMap<>();
    queryMap.put(NODE_ID, id.toString());
    MapSolrParams mapSolrParams = new MapSolrParams(queryMap);

    SolrDocumentList results = null;
    try {
      QueryResponse response = solrClient.query(mapSolrParams);
      results = response.getResults();
    } catch (SolrServerException | IOException e) {
      throw new IllegalStateException(format("Unable to find document with id=%s.", id), e);
    }
    if (results == null || results.isEmpty()) {
      throw new IllegalStateException(format("Document with id=%s does not exist.", id));
    }
    if (results.size() > 1) {
      throw new IllegalStateException(format("There is more than one document with id=%s", id));
    }
    return results.stream().findFirst().get();
  }

  private void insertValues(SolrInputDocument solrDocument, NodeDataBucket dataBucket) {
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
