package core.schema.introspection.creator;

import core.schema.annotations.properties.DateProperty;
import core.schema.descriptions.DatePropertyDescription;

/**
 * Creator class that processes @{@link DateProperty} annotation and returns {@link DatePropertyDescription} instance.
 */
public class DatePropertyDescriptionCreator implements PropertyDescriptionCreator<DatePropertyDescription, DateProperty> {

    @Override
    public DatePropertyDescription processProperty(DateProperty propertyAnnotation,
                                                   String fieldName,
                                                   boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        String format = propertyAnnotation.format();
        return new DatePropertyDescription(resolveName(name, fieldName), nonNull, multiValue, immutable, format);
    }
}
