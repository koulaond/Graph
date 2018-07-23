package core.repository.validation;

import core.schema.descriptions.StringPropertyDescription;

public class StringPropertyValidator implements PropertyValidator<String, StringPropertyDescription> {

  @Override
  public boolean validatePropertyValue(String value, StringPropertyDescription propertyDefinition) {
    int valueLength = value.length();
    return valueLength >= propertyDefinition.getMinLength()
        && valueLength <= propertyDefinition.getMaxLength();
  }
}
