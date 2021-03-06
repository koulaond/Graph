package core.schema.assembly;

import repository.api.definitions.RelationDefinition;
import repository.api.definitions.property.PropertyDefinition;
import model.Direction;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toSet;

public class RelationBuilder {

  private String relationType;
  private String startNodeType;
  private String endNodeType;
  private boolean multiValue;
  private Map<String, PropertyDefinition> propertyDefinitions;
  private Direction direction;

  public RelationBuilder() {
    this.propertyDefinitions = new HashMap<>();
  }

  public RelationBuilder relationType(String relationType) {
    this.relationType = relationType;
    return this;
  }

  public RelationBuilder direction(Direction direction) {
    this.direction = direction;
    return this;
  }

  public RelationBuilder startNodeType(String startNodeType) {
    this.startNodeType = startNodeType;
    return this;
  }

  public RelationBuilder endNodeType(String endNodeType) {
    this.endNodeType = endNodeType;
    return this;
  }

  public RelationBuilder multiValue(boolean multiValue) {
    this.multiValue = multiValue;
    return this;
  }

  public RelationBuilder addProperty(PropertyDefinition propertyDefinition) {
    String propertyName = propertyDefinition.getName();
    if (propertyName == null) {
      throw new IllegalStateException("Missing property name.");
    }
    this.propertyDefinitions.put(propertyName, propertyDefinition);
    return this;
  }

  public RelationDefinition build() {
    return new RelationDefinition(relationType, direction, startNodeType, endNodeType, multiValue, propertyDefinitions.values().stream().collect(toSet()));
  }
}
