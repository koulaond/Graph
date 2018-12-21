package repository.api.definitions;

import java.util.Set;

import repository.api.definitions.property.PropertyDefinition;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import model.Direction;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;
import static model.Direction.UNDIRECTED;

public class RelationDefinition extends AbstractDefinition {

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

  private boolean multiValue;
  /**
   * Set of property description that describe properties, which can be stored in the relation of this type.
   */
  private Set<repository.api.definitions.property.PropertyDefinition> propertyDefinitions;

  public RelationDefinition(String name,
                            Direction direction,
                            String startNodeType,
                            String endNodeType,
                            boolean multiValue,
                            Set<PropertyDefinition> propertyDefinitions) {
    super(name);
    this.direction = requireNonNull(direction);
    this.startNodeType = requireNonNull(startNodeType);
    this.endNodeType = requireNonNull(endNodeType);
    this.multiValue = multiValue;
    this.propertyDefinitions = unmodifiableSet(requireNonNull(propertyDefinitions));
  }

  public Direction getDirection() {
    return direction;
  }

  public Set<repository.api.definitions.property.PropertyDefinition> getPropertyDefinitions() {
    return propertyDefinitions;
  }

  public String getStartNodeType() {
    return startNodeType;
  }

  public String getEndNodeType() {
    return endNodeType;
  }

  public boolean isMultiValue() {
    return multiValue;
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
      return getName().equals(that.getName())
          && crossEquals(startNodeType, endNodeType, that.getStartNodeType(), that.getEndNodeType());
    }

    // In other cases normalize both this and that and compare them fields.
    RelationDefinition normalizedThis = normalizedClone(this);
    RelationDefinition normalizedThat = normalizedClone(that);

    return new EqualsBuilder()
        .append(normalizedThis.getDirection(), normalizedThat.getDirection())
        .append(normalizedThis.getName(), normalizedThat.getName())
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
        .append(name)
        .toHashCode();
  }

  /**
   * Reverts relation in case of INCOMING direction type: 1. INCOMING direction sets to OUTGOING 2. Switches startNode and endNode Otherwise it returns identity
   * object.
   *
   * @return new @{@link RelationDefinition} instance with same values as input (shallow copy).
   */
  private RelationDefinition normalizedClone(RelationDefinition relationDefinition) {
    // TODO move to RelationDefinition class + implement invert method
    Direction direction = relationDefinition.getDirection();
    if (Direction.INCOMING.equals(direction)) {
      return new RelationDefinition(relationDefinition.getName(),
          Direction.OUTGOING,
          relationDefinition.getEndNodeType(),
          relationDefinition.getStartNodeType(),
          relationDefinition.isMultiValue(),
          relationDefinition.getPropertyDefinitions());
    } else {
      return new RelationDefinition(relationDefinition.getName(),
          relationDefinition.getDirection(),
          relationDefinition.getStartNodeType(),
          relationDefinition.getEndNodeType(),
          relationDefinition.isMultiValue(),
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
