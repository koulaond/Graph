package core.schema;

public class CustomSchemaBuilder extends SchemaBuilder {

  
  CustomSchemaBuilder() {
    // Package private constructor
  }

  @Override
  public CustomSchemaBuilder schemaName(String schemaName) {
    return (CustomSchemaBuilder) super.schemaName(schemaName);
  }


}
