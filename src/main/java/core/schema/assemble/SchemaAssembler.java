package core.schema.assemble;

import java.util.HashSet;
import java.util.Set;

import core.schema.assemble.definitions.NodeDefinition;
import core.schema.assemble.definitions.RelationDefinition;

import static java.util.Collections.unmodifiableSet;

public class SchemaAssembler {

  private Set<NodeDefinition> nodes;
  private Set<RelationDefinition> relations;

  private

  public NodeCollector defineNodes() {
    if (nodes != null) {
      throw new IllegalStateException("Nodes are already defined. Reset nodes first.");
    }
    return new NodeCollector(this);
  }

  public Set<NodeDefinition> getNodes() {
    return unmodifiableSet(nodes);
  }

  public Set<RelationDefinition> getRelations() {
    return unmodifiableSet(relations);
  }

  public void reset() {
    this.nodes = null;
    this.relations = null;
  }

  private void addNodes(Set<NodeDefinition> nodes) {
    this.nodes = nodes;
  }

  private void addRelations(Set<RelationDefinition> relations) {
    this.relations = relations;
  }

  private static class NodeCollector {

    private SchemaAssembler schemaAssembler;
    private Set<NodeDefinition> nodeDefinitions;

    public NodeCollector(SchemaAssembler schemaAssembler) {
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

    public void finish() {
      this.schemaAssembler.addNodes(this.nodeDefinitions);
    }
  }

  private static class RelationCollector {
    private SchemaAssembler schemaAssembler;
    private Set<RelationDefinition> relationDefinitions;

    public RelationCollector(SchemaAssembler schemaAssembler) {
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

    public void finish() {
      this.schemaAssembler.addRelations(this.relationDefinitions);
    }
  }

}
