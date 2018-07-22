package core.schema.definitions;

import core.schema.descriptions.PropertyDescription;
import model.Direction;

import java.util.Set;

public class RelationDefinition {

    /**
     * Unique type for relations that are defined by this meta-relation. It is something like class type for relations.
     */
    private String relationType;

    /**
     * Set of property description that describe properties, which can be stored in the relation of this type.
     */
    private Set<PropertyDescription> propertyDescriptions;

    private Direction direction;

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public Set<PropertyDescription> getPropertyDescriptions() {
        return propertyDescriptions;
    }

    public void setPropertyDescriptions(Set<PropertyDescription> propertyDescriptions) {
        this.propertyDescriptions = propertyDescriptions;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RelationDefinition that = (RelationDefinition) o;
        return relationType != null ? relationType.equals(that.relationType) : that.relationType == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (relationType != null ? relationType.hashCode() : 0);
        return result;
    }
}
