package impl.solr;

import org.apache.solr.client.solrj.SolrClient;

import core.repository.SchematicRepository;

public class SchematicRepositoryPersistManager extends RepositoryPersistManager<SchematicRepository> {

  private SimpleRepositoryPersistManager simpleManager;

  public SchematicRepositoryPersistManager(SolrClient client, SchematicRepository repository) {
    super(client, repository);
    this.simpleManager = new SimpleRepositoryPersistManager(client, repository);
  }

  @Override
  public boolean persistRepository() {
    boolean simpleRepositoryPersisted = simpleManager.persistRepository();
    // FAIL fast
    if(!simpleRepositoryPersisted) {
      return false;
    }
    // TODO define schema in solr
    return true;
  }
}
