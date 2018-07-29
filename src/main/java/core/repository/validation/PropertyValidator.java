package core.repository.validation;

import core.schema.descriptions.PropertyDescription;

import java.io.Serializable;

public interface PropertyValidator<T extends Serializable, P extends PropertyDescription<T>> {

  boolean validatePropertyValue(T value, P propertyDescription);
}
