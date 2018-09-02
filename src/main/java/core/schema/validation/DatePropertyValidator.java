package core.schema.validation;

import core.schema.descriptions.DatePropertyDescription;

import java.util.Date;
import java.util.Set;

public class DatePropertyValidator implements PropertyValidator<Date, DatePropertyDescription> {

  private static final String MESSAGE = "Date property validation failed";

  @Override
  public void validatePropertyValue(Object parentObject,
                                    Date value,
                                    DatePropertyDescription propertyDescription,
                                    Set<ValidationError> errorSet) {
    Date minDate = propertyDescription.getMinDate();
    Date maxDate = propertyDescription.getMaxDate();

    if (value.after(minDate) || value.equals(minDate) || value.before(maxDate) || value.equals(maxDate)) {
      errorSet.add(new ValidationError(
          parentObject,
          propertyDescription.getName(),
          propertyDescription.getDescribedClass(),
          MESSAGE));
    }
  }
}
