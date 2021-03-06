package repository.api.definitions;

import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

public class SchemaDefinition extends AbstractDefinition {
  private Set<NodeDefinition> nodeDefinitions;
  private Set<RelationDefinition> relationDefinitions;
  private Map<String, Object> additionalInfo;
  private boolean strict;

  public SchemaDefinition(String name, Set<NodeDefinition> nodeDefinitions, Set<RelationDefinition> relationDefinitions, boolean strict, Map<String, Object> additionalInfo) {
    super(name);
    this.nodeDefinitions = unmodifiableSet(requireNonNull(nodeDefinitions));
    this.relationDefinitions = unmodifiableSet(requireNonNull(relationDefinitions));
    this.additionalInfo = unmodifiableMap(requireNonNull(additionalInfo));
    this.strict = strict;
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

  public boolean isStrict() {
    return strict;
  }
}
