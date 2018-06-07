package core.schema.graphcreators;

import core.schema.descriptions.NodeDescription;
import core.schema.descriptions.RelationshipDescription;
import core.schema.definitions.DefaultNodeDefinition;

import java.util.Collection;
import java.util.Set;

/**
 * Class for building @{@link DefaultNodeDefinition}s from @{@link NodeDescription}s stored in set and connect them each other
 * using their @{@link RelationshipDescription}s.
 */
public interface NodeDefinitionCreator<GD extends GraphDefinition> {

    /**
     * Receives a set of {@link NodeDescription}s and creates the particular {@link DefaultNodeDefinition} for each description.
     * These descriptions are then connected to each other using their {@link RelationshipDescription}s.
     *
     * @param nodeDescriptions node descriptions set
     * @return
     */
    Collection<DefaultNodeDefinition> buildGraph(Set<NodeDescription> nodeDescriptions, GD graphDefinition);
}
