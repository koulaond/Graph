package repository.schema.introspection;

import repository.schema.annotations.properties.NumericProperty;
import repository.schema.descriptions.NumericPropertyDescription;

import java.math.BigDecimal;

public class NumericPropertyDescriptionProcessor
        implements PropertyDescriptionProcessor<NumericPropertyDescription, NumericProperty> {

    @Override
    public NumericPropertyDescription processProperty(NumericProperty propertyAnnotation, boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        long maxValue = propertyAnnotation.maxValue();
        long minValue = propertyAnnotation.minValue();
        return new NumericPropertyDescription(name, nonNull, multiValue, immutable, new BigDecimal(minValue), new BigDecimal(maxValue));
    }
}
