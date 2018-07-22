package core.schema.introspection.creator;

import model.Direction;
import core.schema.annotations.Relationship;
import core.schema.descriptions.PropertyDescription;
import core.schema.descriptions.RelationshipDescription;
import core.schema.introspection.PropertyHolderIntrospector;

import java.util.Set;

import static java.util.Collections.emptySet;

/**
 * Creator class that processes @{@link Relationship} annotation and returns {@link Relationship} instance.
 */
public class RelationshipDescriptionCreator
        implements DescriptionCreator<RelationshipDescription, Relationship> {

    @Override
    public RelationshipDescription processProperty(Relationship relationship,
                                                   String fieldName,
                                                   boolean multiValue) {
        String name = relationship.name();
        Direction direction = relationship.direction();
        boolean nonNull = relationship.nonNull();
        boolean immutable = relationship.immutable();
        Class<?> referencedClass = relationship.referencedClass();
        Class<?> propertyHolderClass = relationship.propertyHolderClass();
        Set<PropertyDescription> properties = null;
        if(Relationship.EmptyHolder.class.equals(propertyHolderClass)) {
            properties = emptySet();
        } else {
            properties = new PropertyHolderIntrospector(propertyHolderClass).introspect();
        }
        return new RelationshipDescription(resolveName(name, fieldName), fieldName, nonNull, multiValue, immutable, referencedClass, properties, direction);
    }
}
