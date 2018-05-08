package repository.schema.descriptions;

import java.util.Set;

public class NodeDescription<T> {

    private Class<T> describedClass;

    private String type;

    private boolean immutable;

    private Set<PropertyDescription> propertyDescriptions;

    private Set<RelationshipDescription> relationshipDescriptions;

    public NodeDescription(Class<T> describedClass,
                           String type,
                           boolean immutable,
                           Set<PropertyDescription> propertyDescriptions,
                           Set<RelationshipDescription> relationshipDescriptions) {
        this.describedClass = describedClass;
        this.type = type;
        this.immutable = immutable;
        this.propertyDescriptions = propertyDescriptions;
        this.relationshipDescriptions = relationshipDescriptions;
    }

    public Class<T> getDescribedClass() {
        return describedClass;
    }

    public String getType() {
        return type;
    }

    public boolean isImmutable() {
        return immutable;
    }

    public Set<PropertyDescription> getPropertyDescriptions() {
        return propertyDescriptions;
    }

    public Set<RelationshipDescription> getRelationshipDescriptions() {
        return relationshipDescriptions;
    }
}
