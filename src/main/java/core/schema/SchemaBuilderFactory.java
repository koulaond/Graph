package core.schema;

public class SchemaBuilderFactory {

    public static ModelSchemaBuilder fromModel() {
        return new ModelSchemaBuilder();
    }

    public static CustomSchemaBuilder custom() {
        return new CustomSchemaBuilder();
    }
}
