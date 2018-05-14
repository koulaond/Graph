package repository.schema.introspection.creator;

import repository.schema.annotations.properties.DateProperty;
import repository.schema.descriptions.DatePropertyDescription;

/**
 * Creator class that processes @{@link DateProperty} annotation and returns {@link DatePropertyDescription} instance.
 */
public class DatePropertyDescriptionCreator implements PropertyDescriptionCreator<DatePropertyDescription, DateProperty> {

    @Override
    public DatePropertyDescription processProperty(DateProperty propertyAnnotation, boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        String format = propertyAnnotation.format();
        return new DatePropertyDescription(name, nonNull, multiValue, immutable, format);
    }
}
