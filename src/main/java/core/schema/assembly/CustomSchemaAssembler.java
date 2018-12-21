package core.schema.assembly;

import repository.api.definitions.NodeDefinition;
import repository.api.definitions.RelationDefinition;
import repository.api.definitions.SchemaDefinition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

public class CustomSchemaAssembler {

  private String name;
  private Set<NodeDefinition> nodes;
  private Set<RelationDefinition> relations;
  private boolean strict;
  private Map<String, Object> additionalInfo;

  public CustomSchemaAssembler() {
    // Default values
    additionalInfo = new HashMap<>();
    strict = true;
  }

  public CustomSchemaAssembler name(String name) {
    this.name = requireNonNull(name);
    return this;
  }

  public CustomSchemaAssembler strict(boolean strict) {
    this.strict = strict;
    return this;
  }

  public CustomSchemaAssembler additionalInfo(String key, Object value) {
    if(key == null || key.isEmpty()) {
      throw new IllegalStateException(String.format("Key is not defined properly: %s", key));
    }
    this.additionalInfo.put(key, requireNonNull(value));
    return this;
  }

  public CustomSchemaAssembler additionalInfo(Map<String, Object> additionalInfo) {
    this.additionalInfo.putAll(requireNonNull(additionalInfo));
    return this;
  }

  public NodeCollector defineNodes() {
    if (nodes != null) {
      throw new IllegalStateException("Nodes are already defined.");
    }
    return new NodeCollector(this);
  }

  public RelationCollector defineRelations() {
    if (relations != null) {
      throw new IllegalStateException("Relations are already defined.");
    }
    return new RelationCollector(this);
  }

  public Set<NodeDefinition> getNodes() {
    return unmodifiableSet(nodes);
  }

  public Set<RelationDefinition> getRelations() {
    return unmodifiableSet(relations);
  }

  public String getName() {
    return name;
  }

  public boolean isStrict() {
    return strict;
  }

  public Map<String, Object> getAdditionalInfo() {
    return unmodifiableMap(additionalInfo);
  }

  public SchemaDefinition assemble() {
    // TODO replace name check by validator
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalStateException("Schema name bad value.");
    }
    if (strict && (nodes == null || nodes.isEmpty())) {
      throw new IllegalStateException("Node(s) mut be defined in strict mode.");
    }
    return new SchemaDefinition(name, unmodifiableSet(nodes), unmodifiableSet(relations), strict, unmodifiableMap(additionalInfo));
  }

  private void addNodes(Set<NodeDefinition> nodes) {
    this.nodes = nodes;
  }

  private void addRelations(Set<RelationDefinition> relations) {
    this.relations = relations;
  }

  public static class NodeCollector {

    private CustomSchemaAssembler schemaAssembler;
    private Set<NodeDefinition> nodeDefinitions;

    private NodeCollector(CustomSchemaAssembler schemaAssembler) {
      this.schemaAssembler = schemaAssembler;
      this.nodeDefinitions = new HashSet<>();
    }

    public NodeCollector node(NodeDefinition nodeDefinition) {
      String nodeType = nodeDefinition.getName();

      if (nodeType == null || nodeType.isEmpty()) {
        throw new IllegalStateException("Missing node type.");
      }

      this.nodeDefinitions.add(nodeDefinition);
      return this;
    }

    public CustomSchemaAssembler finish() {
      this.schemaAssembler.addNodes(this.nodeDefinitions);
      return schemaAssembler;
    }
  }

  public static class RelationCollector {
    private CustomSchemaAssembler schemaAssembler;
    private Set<RelationDefinition> relationDefinitions;

    private RelationCollector(CustomSchemaAssembler schemaAssembler) {
      this.schemaAssembler = schemaAssembler;
      this.relationDefinitions = new HashSet<>();
    }

    public RelationCollector relation(RelationDefinition relationDefinition) {
      String relationType = relationDefinition.getName();

      if (relationType == null || relationType.isEmpty()) {
        throw new IllegalStateException("Missing relation type.");
      }

      if (!nodeTypesAreDefined(relationDefinition.getStartNodeType(), relationDefinition.getEndNodeType())) {
        throw new IllegalStateException("Particular nodes are not defined");

      }
      this.relationDefinitions.add(relationDefinition);
      return this;
    }

    /**
     * Both nodes with startNodeType and endNodeType must be defined.
     */
    private boolean nodeTypesAreDefined(String startNodeType, String endNodeType) {
      Set<NodeDefinition> nodes = this.schemaAssembler.getNodes();
      return nodes.contains(new NodeDefinition(startNodeType)) && nodes.contains(new NodeDefinition(endNodeType));
    }

    public CustomSchemaAssembler finish() {
      this.schemaAssembler.addRelations(this.relationDefinitions);
      return schemaAssembler;
    }
  }

}
