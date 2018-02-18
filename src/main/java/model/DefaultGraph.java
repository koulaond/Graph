package model;

import lombok.NonNull;

import java.util.*;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;

public class DefaultGraph extends AbstractItem implements Graph {

    @NonNull
    private Node initialNode;

    @NonNull
    private Map<UUID, Node> nodes;

    @NonNull
    private Map<UUID, Connection> connections;

    protected DefaultGraph(Node initialNode) {
        this.initialNode = initialNode;
        this.nodes = new HashMap<>();
        this.connections = new HashMap<>();
    }

    @Override
    public Node getInitialNode() {
        return this.initialNode;
    }

    void setInitialNode(@NonNull Node initialNode) {
        if (this.nodes.containsValue(initialNode)) {
            this.initialNode = initialNode;
        } else {
            throw new IllegalStateException(format("Node %s is does not exist in this graph", initialNode.toString()));
        }
    }

    @Override
    public Set<Node> getNodes() {
        return unmodifiableSet(new HashSet<>(this.nodes.values()));
    }

    Node getNode(@NonNull UUID uuid) {
        return this.nodes.get(uuid);
    }

    void addNode(@NonNull Node node) {
        this.nodes.put(node.getUuid(), node);
    }

    void removeNode(@NonNull UUID uuid) {
        this.nodes.remove(uuid);
    }

    boolean containsNode(@NonNull UUID uuid) {
        return this.nodes.containsKey(uuid);
    }

    Connection getConnection(@NonNull UUID uuid) {
        return this.connections.get(uuid);
    }

    void addConnection(@NonNull Connection connection) {
        this.connections.put(connection.getUuid(), connection);
    }

    void removeConnection(UUID uuid) {
        this.connections.remove(uuid);
    }

    boolean containsConnection(@NonNull UUID uuid) {
        return this.connections.containsKey(uuid);
    }

    @Override
    public boolean containsNode(@NonNull Node that) {
        return this.nodes.values()
                .stream()
                .anyMatch(node -> node.includes(that));
    }

    @Override
    public boolean containsConnection(@NonNull Connection that) {
        return this.connections.values()
                .stream()
                .anyMatch(connection -> connection.equals(that));
    }

    @Override
    public Set<Connection> getConnections() {
        return unmodifiableSet(new HashSet<>(this.connections.values()));
    }
}
