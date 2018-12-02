package core.schema;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import core.schema.assemble.definitions.NodeDefinition;
import core.schema.descriptions.NodeDescription;
import core.schema.graphcreators.DefaultNodeDefinitionCreator;
import core.schema.graphcreators.NodeDefinitionCreator;
import core.schema.introspection.PackageIntrospector;

import static core.schema.SchemaAdditionalInfoConstants.PACKAGE;
import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Builder for creating @{@link Schema} from model classes in concrete package.
 */
public class ModelSchemaBuilder extends SchemaBuilder {

    /**
     * Package name / path.
     */
    private String packageName;

    ModelSchemaBuilder() {
        // Package private constructor
    }

    public ModelSchemaBuilder schemaName(String schemaName) {
        return (ModelSchemaBuilder) super.schemaName(schemaName);
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
        Set<NodeDescription> nodeDescriptions = packageIntrospector.introspectPackage(requireNonNull(packageName));
        NodeDefinitionCreator nodeDefinitionCreator = new DefaultNodeDefinitionCreator();
        Map<Class, NodeDefinition> nodeDefinitions = nodeDefinitionCreator.create(nodeDescriptions)
                .stream()
                .collect(toMap(nodeDefinition -> nodeDefinition.getDescribedClass(), identity()));
        return new Schema(schemaName, nodeDefinitions,Collections.singletonMap(PACKAGE, packageName));
    }

}
