package core.schema.assemble.definitions;

import java.util.Set;

import core.schema.assemble.definitions.property.PropertyDefinition;
import model.Direction;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

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

        return new org.apache.commons.lang3.builder.EqualsBuilder()
            .append(relationType, that.relationType)
            .append(startNodeType, that.startNodeType)
            .append(endNodeType, that.endNodeType)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
            .append(relationType)
            .append(startNodeType)
            .append(endNodeType)
            .toHashCode();
    }
}
