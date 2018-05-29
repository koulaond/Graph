package repository.schema.graphbuild;

import repository.schema.metamodel.DefaultGraphDefinition;
import repository.schema.metamodel.Direction;
import repository.schema.descriptions.NodeDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.metamodel.DefaultNodeDefinition;
import repository.schema.metamodel.DefaultRelationDefinition;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * {@inheritDoc}
 */
public class DefaultNodeDefinitionBuilder implements NodeDefinitionBuilder<DefaultGraphDefinition> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<DefaultNodeDefinition> buildGraph(Set<NodeDescription> nodeDescriptions,
                                                        DefaultGraphDefinition graphDefinition) {
        Map<Class, DefaultNodeDefinition> nodes = nodeDescriptions.stream()
                .map(description -> buildNode(description, graphDefinition))
                .collect(toMap(node -> node.getDescribedClass(), identity()));
        nodes.values().forEach(node -> {
            Set<RelationshipDescription> relationshipDescriptions = node.getRelationshipDescriptions();
            // Assign relations to opposite nodes.
            relationshipDescriptions.forEach(relDescription -> {
                DefaultRelationDefinition relation = new DefaultRelationDefinition();
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
     * Builds {@link DefaultNodeDefinition} using values in {@link NodeDescription} param.
     */
    private <T> DefaultNodeDefinition<T> buildNode(NodeDescription<T> description,
                                                   DefaultGraphDefinition graphDefinition) {
        DefaultNodeDefinition defaultNodeDefinition = new DefaultNodeDefinition();
        defaultNodeDefinition.setGraphDefinition(graphDefinition);
        defaultNodeDefinition.setPropertyDescriptions(description.getPropertyDescriptions());
        defaultNodeDefinition.setRelationshipDescriptions(description.getRelationshipDescriptions()
                .stream()
                .map(relationshipDescription -> {
                    DefaultRelationDefinition relation = new DefaultRelationDefinition();
                    relation.setRelationType(relationshipDescription.getType());
                    relation.setPropertyDescriptions(relationshipDescription.getPropertyDescriptions());
                    relation.setDirection(relationshipDescription.getDirection());
                    return relation;
                }).collect(toSet()));
        defaultNodeDefinition.setDescribedClass(description.getDescribedClass());
        defaultNodeDefinition.setMaxCount(description.getMaxCount());
        defaultNodeDefinition.setNodeType(description.getType());
        defaultNodeDefinition.setImmutable(description.isImmutable());
        return defaultNodeDefinition;
    }
}
