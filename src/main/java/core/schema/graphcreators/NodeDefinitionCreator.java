package core.schema.graphcreators;

import core.schema.definitions.GraphDefinition;
import core.schema.descriptions.NodeDescription;
import core.schema.descriptions.RelationshipDescription;
import core.schema.definitions.NodeDefinition;

import java.util.Collection;
import java.util.Set;

/**
 * Class for building @{@link NodeDefinition}s from @{@link NodeDescription}s stored in set and connect them each other
 * using their @{@link RelationshipDescription}s.
 */
public interface NodeDefinitionCreator<GD extends GraphDefinition> {

    /**
     * Receives a set of {@link NodeDescription}s and creates the particular {@link NodeDefinition} for each description.
     * These descriptions are then connected to each other using their {@link RelationshipDescription}s.
     *
     * @param nodeDescriptions node descriptions set
     * @return
     */
    Collection<NodeDefinition> create(Set<NodeDescription> nodeDescriptions, GD graphDefinition);
}
