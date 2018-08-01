package core.repository.processing;

import java.util.Set;

import core.repository.data.DataChangeCollection;
import core.repository.validation.ValidationError;

// TODO reimplement
public class PropertyFinder {

  public DataChangeCollection findProperty(Object data) {
    Set<Property> properties = findSingleProperties(data);
    Set<ValidationError> errors = validateProperties(properties);
    return null;
  }

  private Set<ValidationError> validateProperties(Set<Property> properties) {
    return null;
  }

  private Set<Property> findSingleProperties(Object data) {
    return null;
  }

}
