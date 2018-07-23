package core.repository.validation;

import java.util.Date;

import core.schema.descriptions.DatePropertyDescription;

public class DatePropertyValidator implements PropertyValidator<Date, DatePropertyDescription> {
  @Override
  public boolean validatePropertyValue(Date value, DatePropertyDescription propertyDefinition) {
    Date minDate = propertyDefinition.getMinDate();
    Date maxDate = propertyDefinition.getMaxDate();
    return value.after(minDate) || value.equals(minDate)
        || value.before(maxDate) || value.equals(maxDate);
  }
}
