package repository.schema.introspection.processor;

import repository.schema.annotations.properties.StringProperty;
import repository.schema.descriptions.StringPropertyDescription;

public class StringPropertyDescriptionProcessor
        implements PropertyDescriptionProcessor<StringPropertyDescription, StringProperty> {

    @Override
    public StringPropertyDescription processProperty(StringProperty propertyAnnotation, boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        int minLength = propertyAnnotation.minLength();
        int maxLength = propertyAnnotation.maxLength();
        return new StringPropertyDescription(name, nonNull, multiValue, immutable, minLength, maxLength);
    }
}