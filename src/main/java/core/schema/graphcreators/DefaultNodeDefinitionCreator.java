package core.schema.graphcreators;

import core.schema.descriptions.NodeDescription;
import core.schema.descriptions.RelationshipDescription;
import model.Direction;
import model.definitions.GraphDefinition;
import model.definitions.NodeDefinition;
import model.definitions.RelationDefinition;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * {@inheritDoc}
 */
public class DefaultNodeDefinitionCreator implements NodeDefinitionCreator<GraphDefinition> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<NodeDefinition> buildGraph(Set<NodeDescription> nodeDescriptions,
                                                 GraphDefinition graphDefinition) {
        Map<Class, NodeDefinition> nodes = nodeDescriptions.stream()
                .map(description -> buildNode(description, graphDefinition))
                .collect(toMap(o -> o.getDescribedClass(), identity()));
        nodes.values().forEach(node -> {
            Set<RelationshipDescription> relationshipDescriptions = node.getRelationshipDescriptions();
            // Assign relations to opposite nodes.
            relationshipDescriptions.forEach(relDescription -> {
                RelationDefinition relation = new RelationDefinition();
                relation.setPropertyDescriptions(relDescription.getPropertyDescriptions());
                relation.setRelationType(relDescription.getName());
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
     * Builds {@link NodeDefinition} using values in {@link NodeDescription} param.
     */
    private <T> NodeDefinition<T> buildNode(NodeDescription<T> description,
                                            GraphDefinition graphDefinition) {
        NodeDefinition nodeDefinition = new NodeDefinition();
        nodeDefinition.setGraphDefinition(graphDefinition);
        nodeDefinition.setPropertyDescriptions(description.getPropertyDescriptions());
        nodeDefinition.setRelationshipDescriptions(description.getRelationshipDescriptions()
                .stream()
                .map(relationshipDescription -> {
                    RelationDefinition relation = new RelationDefinition();
                    relation.setRelationType(relationshipDescription.getName());
                    relation.setPropertyDescriptions(relationshipDescription.getPropertyDescriptions());
                    relation.setDirection(relationshipDescription.getDirection());
                    return relation;
                }).collect(toSet()));
        nodeDefinition.setDescribedClass(description.getDescribedClass());
        nodeDefinition.setMaxCount(description.getMaxCount());
        nodeDefinition.setNodeType(description.getType());
        nodeDefinition.setImmutable(description.isImmutable());
        return nodeDefinition;
    }
}
