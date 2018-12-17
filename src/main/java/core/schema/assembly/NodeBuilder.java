package core.schema.assembly;

import java.util.HashMap;
import java.util.Map;

import core.schema.assembly.definitions.NodeDefinition;
import core.schema.assembly.definitions.property.PropertyDefinition;

import static java.util.stream.Collectors.toSet;

public class NodeBuilder {

  private String nodeType;
  private boolean immutable;
  private Long maxCount;
  private Map<String, PropertyDefinition> propertyDefinitions;

  public NodeBuilder() {
    this.propertyDefinitions = new HashMap<>();
  }

  public NodeBuilder nodeType(String nodeType) {
    this.nodeType = nodeType;
    return this;
  }

  public NodeBuilder immutable(boolean immutable) {
    this.immutable = immutable;
    return this;
  }

  public NodeBuilder maxCount(Long maxCount) {
    this.maxCount = maxCount;
    return this;
  }

  public NodeBuilder addProperty(PropertyDefinition propertyDefinition) {
    String propertyName = propertyDefinition.getName();
    if (propertyName == null) {
      throw new IllegalStateException("Missing property name.");
    }
    this.propertyDefinitions.put(propertyName, propertyDefinition);
    return this;
  }

  public NodeDefinition build() {
    return new NodeDefinition(nodeType, immutable, maxCount, propertyDefinitions.values().stream().collect(toSet()));
  }
}
