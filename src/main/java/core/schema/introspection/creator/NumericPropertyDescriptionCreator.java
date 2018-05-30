package core.schema.introspection.creator;

import core.schema.annotations.properties.NumericProperty;
import core.schema.descriptions.NumericPropertyDescription;

import java.math.BigDecimal;

/**
 * Creator class that processes @{@link NumericProperty} annotation and returns {@link NumericPropertyDescription} instance.
 */
public class NumericPropertyDescriptionCreator
        implements PropertyDescriptionCreator<NumericPropertyDescription, NumericProperty> {

    @Override
    public NumericPropertyDescription processProperty(NumericProperty propertyAnnotation,
                                                      String fieldName,
                                                      boolean multiValue) {
        String name = propertyAnnotation.name();
        boolean nonNull = propertyAnnotation.nonNull();
        boolean immutable = propertyAnnotation.immutable();
        long maxValue = propertyAnnotation.maxValue();
        long minValue = propertyAnnotation.minValue();
        return new NumericPropertyDescription(resolveName(name, fieldName), nonNull, multiValue, immutable, new BigDecimal(minValue), new BigDecimal(maxValue));
    }
}
