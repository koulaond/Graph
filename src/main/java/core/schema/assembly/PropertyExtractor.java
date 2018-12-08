package core.schema.assembly;

import java.util.HashSet;
import java.util.Set;

import core.schema.assembly.definitions.property.PropertyDefinition;

public class PropertyExtractor {

  public Set<PropertyDefinition> extractProperties(Class<?> nodeClass) {
    Set<PropertyDefinition> propertyDefinitions = new HashSet<>();
    while(!nodeClass.equals(Object.class)) {
      Set<PropertyDefinition> oneLevelDefinitions = findDefinitions(nodeClass);
      nodeClass = nodeClass.getSuperclass();
    }
    return propertyDefinitions;
  }

  private Set<PropertyDefinition> findDefinitions(Class<?> nodeClass) {
    Set<PropertyDefinition> oneLevelDefinitions = new HashSet<>();

    return oneLevelDefinitions;
  }
}
