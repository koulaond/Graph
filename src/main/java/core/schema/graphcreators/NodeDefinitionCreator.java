package core.schema.graphcreators;

import java.util.Collection;
import java.util.Set;

import core.schema.assemble.definitions.NodeDefinition;
import core.schema.descriptions.NodeDescription;
import core.schema.descriptions.RelationshipDescription;

/**
 * Class for building @{@link NodeDefinition}s from @{@link NodeDescription}s stored in set and connect them each other
 * using their @{@link RelationshipDescription}s.
 */
public interface NodeDefinitionCreator {

    /**
     * Receives a set of {@link NodeDescription}s and creates the particular {@link NodeDefinition} for each description.
     * These descriptions are then connected to each other using their {@link RelationshipDescription}s.
     *
     * @param nodeDescriptions node descriptions set
     * @return
     */
    Collection<NodeDefinition> create(Set<NodeDescription> nodeDescriptions);
}
