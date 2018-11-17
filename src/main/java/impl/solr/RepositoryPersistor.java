package impl.solr;

import org.apache.solr.client.solrj.SolrClient;

import core.repository.Repository;

public abstract class RepositoryPersistor<R extends Repository> {
  protected SolrClient client;
  protected R repository;

  public RepositoryPersistor(SolrClient client, R repository) {
    this.client = client;
    this.repository = repository;
  }

  public abstract boolean persistRepository();
}
