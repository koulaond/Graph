package core.schema.introspection.creator;

import core.schema.annotations.properties.EnumProperty;
import core.schema.descriptions.EnumerationPropertyDescription;

/**
 * Creator class that processes @{@link EnumProperty} annotation and returns {@link EnumerationPropertyDescription} instance.
 */
public class EnumerationDescriptionCreator implements DescriptionCreator<EnumerationPropertyDescription, EnumProperty> {

    @Override
    public EnumerationPropertyDescription processProperty(EnumProperty propertyAnnotation,
                                                          String fieldName,
                                                          boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        Class enumClass = propertyAnnotation.enumClass();
        return new EnumerationPropertyDescription(resolveName(name, fieldName), fieldName, nonNull, multiValue, immutable, enumClass);
    }
}
