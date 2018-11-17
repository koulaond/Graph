package impl.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.request.CollectionAdminRequest;

import core.repository.Repository;

public class SimpleRepositoryPersistor extends RepositoryPersistor<Repository> {

  public SimpleRepositoryPersistor(SolrClient client, Repository repository) {
    super(client, repository);
  }

  @Override
  public boolean persistRepository() {
    CollectionAdminRequest.Create createCollRequest =  CollectionAdminRequest.createCollection(repository.getName(), 1, 1);

    return false;
  }
}
