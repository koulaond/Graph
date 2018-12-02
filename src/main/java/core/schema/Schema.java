package core.schema;

import java.util.Map;

import core.schema.assemble.definitions.NodeDefinition;

import static java.util.Collections.emptyMap;

/**
 * Information about schema.
 */
public class Schema {
    /**
     * Schema name
     */
    private String name;

    /**
     * All defined nodedefinitions.
     */
    private Map<Class, NodeDefinition> nodeDefinitions;

    /**
     * Additional info.
     */
    private Map<String, Object> additionalInfo;

    public Schema(String name,
                  Map<Class, NodeDefinition> nodeDefinitions,
                  Map<String, Object> additionalInfo) {
        this.name = name;
        this.nodeDefinitions = nodeDefinitions;
        this.additionalInfo = additionalInfo;
    }

    public Schema(String name, Map<Class, NodeDefinition> nodeDefinitions) {
       this(name, nodeDefinitions, emptyMap());
    }

    public String getName() {
        return name;
    }

    public Map<Class, NodeDefinition> getNodeDefinitions() {
        return nodeDefinitions;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

}
