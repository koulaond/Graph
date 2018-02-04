package model;

import java.util.*;

public class GraphBuilder {

    protected String label;

    protected Map<String, Object> properties = new HashMap<>();

    private Node initialNode;

    private Map<UUID, Node> nodes = new HashMap<>();

    private Set<Connection> connections = new HashSet<>();

    public GraphBuilder label(String label) {
        this.label = label;
        return this;
    }

    public GraphBuilder property(String key, Object value) {
        this.properties.put(key, value);
        return this;
    }

    public GraphBuilder properties(Map<String, Object> properties) {
        this.properties.putAll(properties);
        return this;
    }

    public GraphBuilder initialNode(Node initialNode) {
        if (!nodes.containsKey(initialNode.getUuid())) {
            throw new IllegalStateException("Node must be added as a sub-node in this graph.");
        }
        this.initialNode = initialNode;
        return this;
    }

    public GraphBuilder node(Node node, boolean initial) {
        this.nodes.put(node.getUuid(), node);
        if (initial) {
            this.initialNode = node;
        }
        return this;
    }

    public GraphBuilder node(Node node) {
        return node(node, false);
    }

    public GraphBuilder connection(Connection connection) {
        this.connections.add(connection);
        return this;
    }

    public DefaultGraph build() {
        DefaultGraph graph = new DefaultGraph(label, initialNode);
        graph.setProperties(properties);
        return graph;
    }
}
