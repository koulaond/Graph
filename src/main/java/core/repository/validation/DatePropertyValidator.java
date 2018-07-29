package core.repository.validation;

import core.schema.descriptions.DatePropertyDescription;

import java.util.Date;
import java.util.Set;

public class DatePropertyValidator implements PropertyValidator<Date, DatePropertyDescription> {
  @Override
  public void validatePropertyValue(Date value, DatePropertyDescription propertyDescription, Set<ValidationError> errorSet) {
    Date minDate = propertyDescription.getMinDate();
    Date maxDate = propertyDescription.getMaxDate();
    if( value.after(minDate) || value.equals(minDate) || value.before(maxDate) || value.equals(maxDate)) {

    }
  }
}
