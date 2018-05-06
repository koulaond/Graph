package repository.schema.introspection;

import repository.schema.annotations.properties.NumericProperty;
import repository.schema.descriptions.NumericPropertyDescription;

public class NumericPropertyDescriptionProcessor
        implements PropertyDescriptionProcessor<NumericPropertyDescription, NumericProperty> {

    @Override
    public NumericPropertyDescription processProperty(NumericProperty propertyAnnotation) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        long maxValue = propertyAnnotation.maxValue();
        long minValue = propertyAnnotation.minValue();
        NumericPropertyDescription description = new NumericPropertyDescription(name, );
        return null;
    }
}
