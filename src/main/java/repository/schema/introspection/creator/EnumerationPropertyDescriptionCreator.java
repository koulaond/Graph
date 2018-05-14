package repository.schema.introspection.creator;

import repository.schema.annotations.properties.EnumProperty;
import repository.schema.descriptions.EnumerationPropertyDescription;

/**
 * Creator class that processes @{@link EnumProperty} annotation and returns {@link EnumerationPropertyDescription} instance.
 */
public class EnumerationPropertyDescriptionCreator implements PropertyDescriptionCreator<EnumerationPropertyDescription, EnumProperty> {

    @Override
    public EnumerationPropertyDescription processProperty(EnumProperty propertyAnnotation, boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        Class enumClass = propertyAnnotation.enumClass();
        return new EnumerationPropertyDescription(name, nonNull, multiValue, immutable, enumClass);
    }
}
