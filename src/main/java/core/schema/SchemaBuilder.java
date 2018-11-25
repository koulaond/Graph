package core.schema;


public abstract class SchemaBuilder {

  /**
   * Name for schema.
   */
  protected String schemaName;

  SchemaBuilder() {
    // Package private constructor
  }

  public SchemaBuilder schemaName(String schemaName) {
    this.schemaName = schemaName;
    return this;
  }
}
