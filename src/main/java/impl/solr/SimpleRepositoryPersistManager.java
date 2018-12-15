package impl.solr;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;
import org.apache.solr.client.solrj.request.schema.FieldTypeDefinition;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;

import core.repository.GraphContainer;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Slf4j
public class SimpleRepositoryPersistManager extends RepositoryPersistManager<GraphContainer> {

  public SimpleRepositoryPersistManager(SolrClient client, GraphContainer graphContainer) {
    super(client, graphContainer);
  }

  @Override
  public boolean persistRepository() {
    try {
      CollectionAdminRequest.Create createCollRequest = CollectionAdminRequest.createCollection(repository.getName(), 1, 1);
      createCollRequest.process(client);
      FieldTypeDefinition idDefinition = fieldTypeDefinition("uuid", "solr.UUIDField", TRUE.toString(), TRUE.toString(), FALSE.toString(), TRUE.toString());
      SchemaRequest.AddFieldType addFieldType = new SchemaRequest.AddFieldType(idDefinition);
      addFieldType.process(client);
    } catch (SolrServerException | IOException e) {
//      log.error("GraphContainer {} was not created/persisted. Some error occurred.");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Creates new field type definition.
   * @param nameValue value for name
   * @param typeValue value for type
   * @param indexedValue true/false
   * @param storedValue true/false
   * @param multiValuedValue true/false
   * @param requiredValue true/false
   * @return new field type definition instance
   */
  private FieldTypeDefinition fieldTypeDefinition(String nameValue, String typeValue, String indexedValue, String storedValue, String multiValuedValue, String requiredValue) {
    FieldTypeDefinition fieldType = new FieldTypeDefinition();
    Map<String, Object> attributes = new HashMap<>();
    // Define attributes
    attributes.put(NAME, nameValue);
    attributes.put(TYPE, typeValue);
    attributes.put(INDEXED, indexedValue);
    attributes.put(STORED, storedValue);
    attributes.put(MULTIVALUED, multiValuedValue);
    attributes.put(REQUIRED, requiredValue);
    fieldType.setAttributes(attributes);
//    TODO Define analyzer and tokenizer
//    AnalyzerDefinition analyzer = new AnalyzerDefinition();
//    Map<String, Object> tokenizer = new HashMap<>();
//    analyzer.setTokenizer(tokenizer);
//    fieldType.setAnalyzer(analyzer);
    return fieldType;
  }
}
