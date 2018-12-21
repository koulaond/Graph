package core.schema.assembly.transformer;

import java.math.BigDecimal;

import core.schema.annotations.properties.NumericProperty;
import repository.api.definitions.property.NumericPropertyDefinition;

public class NumericPropertyAnnotationTransformer implements PropertyAnnotationTransformer<NumericProperty> {

  @Override
  public NumericPropertyDefinition transformToDefinition(NumericProperty annotation, boolean multiValue) {
    return new NumericPropertyDefinition(
        annotation.name(),
        annotation.nonNull(),
        multiValue, annotation.immutable(),
        new BigDecimal(annotation.minValue()),
        new BigDecimal(annotation.maxValue()));
  }
}
