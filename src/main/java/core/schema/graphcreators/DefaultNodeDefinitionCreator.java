package core.schema.graphcreators;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import core.schema.assemble.definitions.NodeDefinition;
import core.schema.assemble.definitions.RelationDefinition;
import core.schema.descriptions.NodeDescription;
import core.schema.descriptions.PropertyDescription;
import model.Direction;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * {@inheritDoc}
 */
public class DefaultNodeDefinitionCreator implements NodeDefinitionCreator {

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<NodeDefinition> create(Set<NodeDescription> nodeDescriptions) {
    Set<NodeDefinition> nodeDefinitions = new HashSet<>();
    nodeDescriptions.stream().forEach(nodeDescription -> nodeDefinitions.add(buildNode(nodeDescription)));
    Map<Class, NodeDefinition> nodeDefinitionMap = new HashMap<>();
    nodeDefinitions.forEach(nodeDefinition -> nodeDefinitionMap.put(nodeDefinition.getDescribedClass(), nodeDefinition));
    nodeDefinitionMap.values().forEach(node -> {
      Collection<RelationDefinition> relationshipDescriptions = node.getCache()
          .getRelationshipDescriptionsByPropertyName()
          .values();
      // Assign relations to opposite nodes.
      relationshipDescriptions.forEach(relDescription -> {
        RelationDefinition relation = new RelationDefinition();
        relation.setPropertyDescriptions(relDescription.getPropertyDescriptions());
        relation.setOwnerFieldName(relDescription.getOwnerFieldName());
        relation.setRelationType(relDescription.getRelationType());
        switch (relDescription.getDirection()) {
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
    return nodeDefinitionMap.values();
  }

  /**
   * Builds {@link NodeDefinition} using values in {@link NodeDescription} param.
   */
  private <T> NodeDefinition<T> buildNode(NodeDescription<T> description) {
    NodeDefinition nodeDefinition = new NodeDefinition();
    Map<String, PropertyDescription> propertyDescriptionMap = description.getPropertyDescriptions().stream()
        .collect(toMap(propertyDescription -> propertyDescription.getName(), identity()));
    Map<String, RelationDefinition> relationDefinitionMap = description.getRelationshipDescriptions()
        .stream()
        .map(relationshipDescription -> {
          RelationDefinition relation = new RelationDefinition();
          relation.setOwnerFieldName(relationshipDescription.getOwnerFieldName());
          relation.setRelationType(relationshipDescription.getName());
          relation.setPropertyDescriptions(relationshipDescription.getPropertyDescriptions());
          relation.setDirection(relationshipDescription.getDirection());
          return relation;
        }).collect(toMap(relationDefinition -> relationDefinition.getRelationType(), identity()));
    nodeDefinition.cache(propertyDescriptionMap, relationDefinitionMap);
    nodeDefinition.setDescribedClass(description.getDescribedClass());
    nodeDefinition.setMaxCount(description.getMaxCount());
    nodeDefinition.setNodeType(description.getType());
    nodeDefinition.setImmutable(description.isImmutable());
    return nodeDefinition;
  }
}
