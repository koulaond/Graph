package impl.solr;

import org.apache.solr.client.solrj.SolrClient;

import core.repository.Repository;

/**
 * Manager class for persisting metadata about single repository.
 * @param <R> @{@link Repository} type
 */
public abstract class RepositoryPersistManager<R extends Repository> {

  // Field type attributes
  public static final String NAME = "name";
  public static final String TYPE = "type";
  public static final String INDEXED = "indexed";
  public static final String REQUIRED = "required";
  public static final String MULTIVALUED = "multiValued";
  public static final String STORED = "stored";

  protected SolrClient client;
  protected R repository;

  public RepositoryPersistManager(SolrClient client, R repository) {
    this.client = client;
    this.repository = repository;
  }

  /**
   * Tries to create repository in solr.
   * @return true if repository was created in solr, otherwise it returns true and logs error message
   */
  public abstract boolean persistRepository();
}
