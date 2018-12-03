package core.schema.assemble.definitions;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

public class SchemaDefinition {
  private String name;
  private Set<NodeDefinition> nodeDefinitions;
  private Set<RelationDefinition> relationDefinitions;
  private Map<String, Object> additionalInfo;

  public SchemaDefinition(String name, Set<NodeDefinition> nodeDefinitions, Set<RelationDefinition> relationDefinitions, Map<String, Object> additionalInfo) {
    this.name = requireNonNull(name);
    this.nodeDefinitions = unmodifiableSet(requireNonNull(nodeDefinitions));
    this.relationDefinitions = unmodifiableSet(requireNonNull(relationDefinitions));
    this.additionalInfo = unmodifiableMap(requireNonNull(additionalInfo));
  }

  public String getName() {
    return name;
  }

  public Set<NodeDefinition> getNodeDefinitions() {
    return nodeDefinitions;
  }

  public Set<RelationDefinition> getRelationDefinitions() {
    return relationDefinitions;
  }

  public Map<String, Object> getAdditionalInfo() {
    return additionalInfo;
  }
}
