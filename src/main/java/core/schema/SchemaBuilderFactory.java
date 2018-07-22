package core.schema;

public class SchemaBuilderFactory {

    public static ModelSchemaBuilder fromModel() {
        return new ModelSchemaBuilder();
    }
}
