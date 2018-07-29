package core.repository.validation;

import java.math.BigDecimal;

import core.schema.descriptions.NumericPropertyDescription;

public class NumericPropertyValidator implements PropertyValidator<BigDecimal, NumericPropertyDescription> {

  private static final int THIS_LESS = -1;
  private static final int THIS_EQUALS = 0;
  private static final int THIS_GREATER = 1;

  @Override
  public boolean validatePropertyValue(BigDecimal value, NumericPropertyDescription propertyDescription) {
    int compareToMax = value.compareTo(propertyDescription.getMaxValue());
    int compareToMin = value.compareTo(propertyDescription.getMinValue());
    return compareToMax == THIS_LESS
        || compareToMax == THIS_EQUALS
        || compareToMin == THIS_GREATER
        || compareToMin == THIS_EQUALS;
  }
}
