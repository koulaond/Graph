package repository.schema.graphbuild;

import repository.schema.descriptions.NodeDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.metamodel.MetaNode;

import java.util.Collection;
import java.util.Set;

/**
 * Class for building @{@link MetaNode}s from @{@link NodeDescription}s stored in set and connect them each other
 * using their @{@link RelationshipDescription}s.
 */
public interface MetaNodeBuilder {

    /**
     * Receives a set of {@link NodeDescription}s and creates the particular {@link MetaNode} for each description.
     * These descriptions are then connected to each other using their {@link RelationshipDescription}s.
     *
     * @param nodeDescriptions node descriptions set
     * @return
     */
    Collection<MetaNode> buildGraph(Set<NodeDescription> nodeDescriptions);
}
