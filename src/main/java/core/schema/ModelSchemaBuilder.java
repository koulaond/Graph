package core.schema;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import core.schema.definitions.GraphDefinition;
import core.schema.definitions.NodeDefinition;
import core.schema.descriptions.NodeDescription;
import core.schema.graphcreators.DefaultGraphDefinitionCreator;
import core.schema.graphcreators.DefaultNodeDefinitionCreator;
import core.schema.graphcreators.GraphDefinitionCreator;
import core.schema.graphcreators.NodeDefinitionCreator;
import core.schema.introspection.PackageIntrospector;

import static core.schema.SchemaAdditionalInfoConstants.PACKAGE;
import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Builder for creating @{@link Schema} from concrete package.
 */
public class ModelSchemaBuilder {

    /**
     * Name for schema.
     */
    private String schemaName;

    /**
     * Package name / path.
     */
    private String packageName;

    ModelSchemaBuilder() {
    }

    public ModelSchemaBuilder schemaName(String schemaName) {
        this.schemaName = schemaName;
        return this;
    }

    public ModelSchemaBuilder packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    /**
     * Returns generated @{@link Schema} instance from the package.
     */
    public Schema build() {
        PackageIntrospector packageIntrospector = new PackageIntrospector();
        GraphDefinitionCreator<GraphDefinition> graphDefinitionCreator = new DefaultGraphDefinitionCreator();
        GraphDefinition graphDefinition = graphDefinitionCreator.create();
        Set<NodeDescription> nodeDescriptions = packageIntrospector.introspectPackage(requireNonNull(packageName));
        NodeDefinitionCreator<GraphDefinition> nodeDefinitionCreator = new DefaultNodeDefinitionCreator();
        Map<Class, NodeDefinition> nodeDefinitions = nodeDefinitionCreator.create(nodeDescriptions, graphDefinition)
                .stream()
                .collect(toMap(nodeDefinition -> nodeDefinition.getDescribedClass(), identity()));
        return new Schema(schemaName, graphDefinition, nodeDefinitions,Collections.singletonMap(PACKAGE, packageName));
    }

}
