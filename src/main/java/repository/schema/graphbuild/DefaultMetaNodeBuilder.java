package repository.schema.graphbuild;

import repository.schema.Direction;
import repository.schema.descriptions.NodeDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.metamodel.MetaNode;
import repository.schema.metamodel.MetaRelation;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * {@inheritDoc}
 */
public class DefaultMetaNodeBuilder implements MetaNodeBuilder {

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<MetaNode> buildGraph(Set<NodeDescription> nodeDescriptions) {
        Map<Class, MetaNode> nodes = nodeDescriptions.stream()
                .map(description -> buildNode(description))
                .collect(toMap(node -> node.getDescribedClass(), identity()));
        nodes.values().forEach(node -> {
            Set<RelationshipDescription> relationshipDescriptions = node.getRelationshipDescriptions();
            // Assign relations to opposite nodes.
            relationshipDescriptions.forEach(relDescription -> {
                MetaRelation relation = new MetaRelation();
                relation.setPropertyDescriptions(relDescription.getPropertyDescriptions());
                relation.setRelationType(relDescription.getType());
                switch (relDescription.getDirection()){
                    case UNDIRECTED:
                        relation.setDirection(Direction.UNDIRECTED);
                        break;
                    case OUTGOING:
                        relation.setDirection(Direction.INCOMING);
                        break;
                    case INCOMING:
                        relation.setDirection(Direction.OUTGOING);
                        break;
                }
            });
        });
        return nodes.values();
    }

    /**
     * Builds {@link MetaNode} using values in {@link NodeDescription} param.
     */
    private <T> MetaNode<T> buildNode(NodeDescription<T> description) {
        MetaNode metaNode = new MetaNode();
        metaNode.setPropertyDescriptions(description.getPropertyDescriptions());
        metaNode.setRelationshipDescriptions(description.getRelationshipDescriptions()
                .stream()
                .map(relationshipDescription -> {
                    MetaRelation relation = new MetaRelation();
                    relation.setRelationType(relationshipDescription.getType());
                    relation.setPropertyDescriptions(relationshipDescription.getPropertyDescriptions());
                    relation.setDirection(relationshipDescription.getDirection());
                    return relation;
                }).collect(toSet()));
        metaNode.setDescribedClass(description.getDescribedClass());
        metaNode.setMaxCount(description.getMaxCount());
        metaNode.setNodeType(description.getType());
        metaNode.setImmutable(description.isImmutable());
        return metaNode;
    }
}
