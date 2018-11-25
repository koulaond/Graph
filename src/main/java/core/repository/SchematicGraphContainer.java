package core.repository;

import core.schema.Schema;

public class SchematicGraphContainer extends GraphContainer {

  private Schema schema;

  public SchematicGraphContainer(RepositoryConnector repositoryConnector, Schema schema) {
    super(repositoryConnector, schema.getName());
    this.schema = schema;
  }

  public Schema getSchema() {
    return schema;
  }
}
