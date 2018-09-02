package core.schema.validation;

import java.util.Set;

import core.schema.descriptions.StringPropertyDescription;

public class StringPropertyValidator implements PropertyValidator<String, StringPropertyDescription> {

  private static final String MESSAGE = "String property validation failed";

  @Override
  public void validatePropertyValue(Object parentObject,
                                       String value,
                                       StringPropertyDescription propertyDescription,
                                       Set<ValidationError> errorSet) {
    int valueLength = value.length();
    if(valueLength >= propertyDescription.getMinLength()
        && valueLength <= propertyDescription.getMaxLength()) {
      errorSet.add(new ValidationError(
          parentObject,
          propertyDescription.getName(),
          propertyDescription.getDescribedClass(),
          MESSAGE));
    }
  }
}
