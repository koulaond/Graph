package repository.schema.introspection.processor;

import repository.schema.Direction;
import repository.schema.annotations.Relationship;
import repository.schema.descriptions.PropertyDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.introspection.PropertyHolderIntrospector;

import java.util.Set;

public class RelationshipDescriptionProcessor
        implements PropertyDescriptionProcessor<RelationshipDescription, Relationship> {

    @Override
    public RelationshipDescription processProperty(Relationship relationship, boolean multiValue) {
        String name = relationship.name();
        Direction direction = relationship.direction();
        boolean nonNull = relationship.nonNull();
        boolean immutable = relationship.immutable();
        Class<?> propertyHolderClass = relationship.propertyHolderClass();
        Class<?> referencedClass = relationship.referencedClass();
        PropertyHolderIntrospector introspector = new PropertyHolderIntrospector(propertyHolderClass);
        Set<PropertyDescription> properties = introspector.introspect();
        return new RelationshipDescription(name, nonNull, multiValue, immutable, referencedClass, properties, direction);
    }
}
