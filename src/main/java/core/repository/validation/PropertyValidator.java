package core.repository.validation;

import java.io.Serializable;

import core.schema.descriptions.PropertyDescription;

public interface PropertyValidator<T extends Serializable, P extends PropertyDescription<T>> {

  boolean validatePropertyValue(T value, P propertyDefinition);
}
