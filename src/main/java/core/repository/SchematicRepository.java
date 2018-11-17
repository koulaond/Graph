package core.repository;

import core.schema.Schema;

public class SchematicRepository extends Repository {

  private Schema schema;

  public SchematicRepository(RepositoryConnector repositoryConnector, Schema schema) {
    super(repositoryConnector, schema.getName());
    this.schema = schema;
  }

  public Schema getSchema() {
    return schema;
  }
}
