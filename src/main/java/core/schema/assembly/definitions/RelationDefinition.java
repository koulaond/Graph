package core.schema.assembly.definitions;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import core.schema.assembly.definitions.property.PropertyDefinition;
import model.Direction;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;
import static model.Direction.UNDIRECTED;

public class RelationDefinition {

  /**
   * Unique type for relations that are defined by this meta-relation. It is something like class type for relations.
   */
  private String relationType;


  /**
   * Relationship direction.
   */
  private Direction direction;

  /**
   * Starting node type.
   */
  private String startNodeType;

  /**
   * Ending node type.
   */
  private String endNodeType;
  /**
   * Set of property description that describe properties, which can be stored in the relation of this type.
   */
  private Set<PropertyDefinition> propertyDefinitions;

  public RelationDefinition(String relationType,
                            Direction direction,
                            String startNodeType,
                            String endNodeType,
                            Set<PropertyDefinition> propertyDefinitions) {
    this.relationType = requireNonNull(relationType);
    this.direction = requireNonNull(direction);
    this.startNodeType = requireNonNull(startNodeType);
    this.endNodeType = requireNonNull(endNodeType);
    this.propertyDefinitions = unmodifiableSet(requireNonNull(propertyDefinitions));
  }

  public String getRelationType() {
    return relationType;
  }

  public Direction getDirection() {
    return direction;
  }

  public Set<PropertyDefinition> getPropertyDefinitions() {
    return propertyDefinitions;
  }

  public String getStartNodeType() {
    return startNodeType;
  }

  public String getEndNodeType() {
    return endNodeType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelationDefinition that = (RelationDefinition) o;

    // In case of both directions are UNDIRECTED provide crossEquals on start and end nodes on both relations.
    if (UNDIRECTED.equals(getDirection()) && UNDIRECTED.equals(that.getDirection())) {
      return getRelationType().equals(that.getRelationType())
          && crossEquals(startNodeType, endNodeType, that.getStartNodeType(), that.getEndNodeType());
    }

    // In other cases normalize both this and that and compare them fields.
    RelationDefinition normalizedThis = normalizedClone(this);
    RelationDefinition normalizedThat = normalizedClone(that);

    return new EqualsBuilder()
        .append(normalizedThis.getDirection(), normalizedThat.getDirection())
        .append(normalizedThis.getRelationType(), normalizedThat.getRelationType())
        .append(normalizedThis.getStartNodeType(), normalizedThat.getStartNodeType())
        .append(normalizedThis.getEndNodeType(), normalizedThat.getEndNodeType())
        .build();
  }

  @Override
  public int hashCode() {
    Direction direction = getDirection();
    int nodesHashCode;
    if (UNDIRECTED.equals(direction)) {
      // To hold the hashCode contract and cross-equality of target nodes, UNDIRECTED relation must have same hash no matter what the order of target nodes is
      nodesHashCode = getStartNodeType().hashCode() + getEndNodeType().hashCode();
    } else {
      // Normalize this for correct order startNode-endNode
      RelationDefinition normalizedThis = normalizedClone(this);
      nodesHashCode = new HashCodeBuilder().append(normalizedThis.getStartNodeType()).append(normalizedThis.getEndNodeType()).build();
    }
    return new HashCodeBuilder(17, 37)
        .append(direction)
        .append(nodesHashCode)
        .append(relationType)
        .toHashCode();
  }

  /**
   * Reverts relation in case of INCOMING direction type: 1. INCOMING direction sets to OUTGOING 2. Switches startNode and endNode Otherwise it returns identity
   * object.
   *
   * @return new @{@link RelationDefinition} instance with same values as input (shallow copy).
   */
  private RelationDefinition normalizedClone(RelationDefinition relationDefinition) {
    Direction direction = relationDefinition.getDirection();
    if (Direction.INCOMING.equals(direction)) {
      return new RelationDefinition(relationDefinition.getRelationType(),
          Direction.OUTGOING,
          relationDefinition.getEndNodeType(),
          relationDefinition.getStartNodeType(),
          relationDefinition.getPropertyDefinitions());
    } else {
      return new RelationDefinition(relationDefinition.getRelationType(),
          relationDefinition.getDirection(),
          relationDefinition.getStartNodeType(),
          relationDefinition.getEndNodeType(),
          relationDefinition.getPropertyDefinitions());
    }
  }

  /**
   * Returns true if:
   * <p>
   * (thisStartNode eq thatStartNode AND thisEndNode eq thatEndNode) OR (thisStartNode eq thatEndNode AND thisEndNode eq thatStartNode)
   * <p>
   * else false.
   */
  private boolean crossEquals(String thisStartNode, String thisEndNode, String thatStartNode, String thatEndNode) {
    if (thisStartNode.equals(thatStartNode)) {
      return thisEndNode.equals(thatEndNode);
    } else if (thisStartNode.equals(thatEndNode)) {
      return thisEndNode.equals(thatStartNode); // Cross equals
    }
    return false;
  }
}
