package core.schema.assemble;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import core.schema.assemble.definitions.NodeDefinition;
import core.schema.assemble.definitions.RelationDefinition;
import core.schema.assemble.definitions.SchemaDefinition;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

public class SchemaAssembler {

  private Set<NodeDefinition> nodes;
  private Set<RelationDefinition> relations;
  private String name;
  private Map<String, Object> additionalInfo;


  public SchemaAssembler name(String name) {
    this.name = name;
    return this;
  }

  public SchemaAssembler additionalInfo(String key, Object value) {
    this.additionalInfo.put(key, value);
    return this;
  }

  public SchemaAssembler additionalInfo(Map<String, Object> additionalInfo) {
    this.additionalInfo.putAll(additionalInfo);
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
      throw new IllegalStateException("RElations are already defined.");
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

  public Map<String, Object> getAdditionalInfo() {
    return unmodifiableMap(additionalInfo);
  }

  public SchemaDefinition assemble() {
    return new SchemaDefinition(name, unmodifiableSet(nodes), unmodifiableSet(relations), unmodifiableMap(additionalInfo));
  }

  private void addNodes(Set<NodeDefinition> nodes) {
    this.nodes = nodes;
  }

  private void addRelations(Set<RelationDefinition> relations) {
    this.relations = relations;
  }

  public static class NodeCollector {

    private SchemaAssembler schemaAssembler;
    private Set<NodeDefinition> nodeDefinitions;

    private NodeCollector(SchemaAssembler schemaAssembler) {
      this.schemaAssembler = schemaAssembler;
      this.nodeDefinitions = new HashSet<>();
    }

    public NodeCollector node(NodeDefinition nodeDefinition) {
      String nodeType = nodeDefinition.getNodeType();

      if (nodeType == null || nodeType.isEmpty()) {
        throw new IllegalStateException("Missing node type.");
      }

      this.nodeDefinitions.add(nodeDefinition);
      return this;
    }

    public SchemaAssembler finish() {
      this.schemaAssembler.addNodes(this.nodeDefinitions);
      return schemaAssembler;
    }
  }

  public static class RelationCollector {
    private SchemaAssembler schemaAssembler;
    private Set<RelationDefinition> relationDefinitions;

    private RelationCollector(SchemaAssembler schemaAssembler) {
      this.schemaAssembler = schemaAssembler;
      this.relationDefinitions = new HashSet<>();
    }

    public RelationCollector relation(RelationDefinition relationDefinition) {
      String relationType = relationDefinition.getRelationType();

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

    public SchemaAssembler finish() {
      this.schemaAssembler.addRelations(this.relationDefinitions);
      return schemaAssembler;
    }
  }

}
