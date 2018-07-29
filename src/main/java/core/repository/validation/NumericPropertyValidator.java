package core.repository.validation;

import java.math.BigDecimal;
import java.util.Set;

import core.schema.descriptions.NumericPropertyDescription;

public class NumericPropertyValidator implements PropertyValidator<BigDecimal, NumericPropertyDescription> {

  private static final int THIS_LESS = -1;
  private static final int THIS_EQUALS = 0;
  private static final int THIS_GREATER = 1;

  private static final String MESSAGE = "Numeric property validation failed";

  @Override
  public void validatePropertyValue(Object parentObject,
                                    BigDecimal value,
                                    NumericPropertyDescription propertyDescription,
                                    Set<ValidationError> errorSet) {
    int compareToMax = value.compareTo(propertyDescription.getMaxValue());
    int compareToMin = value.compareTo(propertyDescription.getMinValue());
    if( compareToMax == THIS_LESS
        || compareToMax == THIS_EQUALS
        || compareToMin == THIS_GREATER
        || compareToMin == THIS_EQUALS) {
      errorSet.add(new ValidationError(
          parentObject,
          propertyDescription.getName(),
          propertyDescription.getDescribedClass(),
          MESSAGE));
    }
  }
}
