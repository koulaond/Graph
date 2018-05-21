package repository.schema;

import repository.schema.descriptions.NodeDescription;
import java.util.Map;

public class SchemaDefinition {

    private String name;
    private Map<Class, NodeDescription> typeDescriptions;
}
