package core.repository.validation;

import core.schema.descriptions.PropertyDescription;

import java.io.Serializable;
import java.util.Set;

public interface PropertyValidator<T extends Serializable, P extends PropertyDescription<T>> {

  void validatePropertyValue(T value, P propertyDescription, Set<ValidationError> errorSet);
}
