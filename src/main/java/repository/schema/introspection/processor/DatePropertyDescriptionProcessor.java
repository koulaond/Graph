package repository.schema.introspection.processor;

import repository.schema.annotations.properties.DateProperty;
import repository.schema.descriptions.DatePropertyDescription;

public class DatePropertyDescriptionProcessor implements PropertyDescriptionProcessor<DatePropertyDescription, DateProperty> {
    @Override
    public DatePropertyDescription processProperty(DateProperty propertyAnnotation, boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        String format = propertyAnnotation.format();
        return new DatePropertyDescription(name, nonNull, multiValue, immutable, format);
    }
}
