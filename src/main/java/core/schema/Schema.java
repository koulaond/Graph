package core.schema;

import java.util.Map;

import core.schema.definitions.GraphDefinition;
import core.schema.definitions.NodeDefinition;

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
     * Graph information.
     */
    private GraphDefinition graphDefinition;

    /**
     * All defined nodedefinitions.
     */
    private Map<Class, NodeDefinition> nodeDefinitions;

    /**
     * Additional info.
     */
    private Map<String, Object> additionalInfo;

    public Schema(String name,
                  GraphDefinition graphDefinition,
                  Map<Class, NodeDefinition> nodeDefinitions,
                  Map<String, Object> additionalInfo) {
        this.name = name;
        this.graphDefinition = graphDefinition;
        this.nodeDefinitions = nodeDefinitions;
        this.additionalInfo = additionalInfo;
    }

    public Schema(String name, GraphDefinition graphDefinition, Map<Class, NodeDefinition> nodeDefinitions) {
       this(name, graphDefinition, nodeDefinitions, emptyMap());
    }

    public String getName() {
        return name;
    }

    public GraphDefinition getGraphDefinition() {
        return graphDefinition;
    }

    public Map<Class, NodeDefinition> getNodeDefinitions() {
        return nodeDefinitions;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

}
