package repository.api.definitions;

public class GraphDefinition extends AbstractDefinition {

  private SchemaDefinition schemaDefinition;
  private GraphMetadata metadata;

  public GraphDefinition(String name) {
    super(name);
  }
}
