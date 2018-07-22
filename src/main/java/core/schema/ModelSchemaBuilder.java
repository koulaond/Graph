package core.schema;

import core.schema.descriptions.NodeDescription;
import core.schema.graphcreators.DefaultGraphDefinitionCreator;
import core.schema.graphcreators.DefaultNodeDefinitionCreator;
import core.schema.graphcreators.GraphDefinitionCreator;
import core.schema.graphcreators.NodeDefinitionCreator;
import core.schema.introspection.PackageIntrospector;
import core.schema.definitions.GraphDefinition;
import core.schema.definitions.NodeDefinition;

import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class ModelSchemaBuilder {
    private String schemaName;
    private String packageName;

    private ModelSchemaBuilder() {
        // No instances of builder!!
        throw new IllegalStateException(format("No instances of %s!", this.getClass().getName()));
    }

    public ModelSchemaBuilder schemaName(String schemaName) {
        this.schemaName = schemaName;
        return this;
    }

    public ModelSchemaBuilder packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Schema build() {
        PackageIntrospector packageIntrospector = new PackageIntrospector();
        GraphDefinitionCreator<GraphDefinition> graphDefinitionCreator = new DefaultGraphDefinitionCreator();
        GraphDefinition graphDefinition = graphDefinitionCreator.create();
        Set<NodeDescription> nodeDescriptions = packageIntrospector.introspectPackage(requireNonNull(packageName));
        NodeDefinitionCreator<GraphDefinition> nodeDefinitionCreator = new DefaultNodeDefinitionCreator();
        Map<Class, NodeDefinition> nodeDefinitions = nodeDefinitionCreator.create(nodeDescriptions, graphDefinition)
                .stream()
                .collect(toMap(nodeDefinition -> nodeDefinition.getDescribedClass(), identity()));
        return new Schema(schemaName, graphDefinition, nodeDefinitions);
    }

}
