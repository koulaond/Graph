package impl.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;

import static java.util.Arrays.asList;

public class SolrTest {


  public static void main(String[] args) throws IOException, SolrServerException {
    SolrClient client = new CloudSolrClient.Builder(asList("http://localhost:8983/solr"))
        .withConnectionTimeout(10000)
        .withSocketTimeout(60000)
        .build();
    CollectionAdminRequest.Create createCollRequest =  CollectionAdminRequest.createCollection("graphY", 1, 1);
    createCollRequest.process(client);
  }
}
