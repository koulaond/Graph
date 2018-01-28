package api;

import lombok.NonNull;

import java.util.*;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;

public class DefaultGraph<N extends Node, C extends Connection> extends AbstractItem implements Graph<N, C> {

    @NonNull
    private N initialNode;

    @NonNull
    private Map<UUID, N> nodes;

    @NonNull
    private Map<UUID, C> connections;

    protected DefaultGraph(String label, N initialNode) {
        super(label);
        this.initialNode = initialNode;
        this.nodes = new HashMap<>();
        this.connections = new HashMap<>();
    }

    @Override
    public N getInitialNode() {
        return this.initialNode;
    }

    void setInitialNode(@NonNull N initialNode) {
        if (this.nodes.containsValue(initialNode)) {
            this.initialNode = initialNode;
        } else {
            throw new IllegalStateException(format("Node %s is does not exist in this graph", initialNode.toString()));
        }
    }

    @Override
    public Set<N> getNodes() {
        return unmodifiableSet(new HashSet<>(this.nodes.values()));
    }

    N getNode(@NonNull UUID uuid) {
        return this.nodes.get(uuid);
    }

    void addNode(@NonNull N node) {
        this.nodes.put(node.getUuid(), node);
    }

    void removeNode(@NonNull UUID uuid) {
        this.nodes.remove(uuid);
    }

    boolean containsNode(@NonNull UUID uuid) {
        return this.nodes.containsKey(uuid);
    }

    C getConnection(@NonNull UUID uuid) {
        return this.connections.get(uuid);
    }

    void addConnection(@NonNull C connection) {
        this.connections.put(connection.getUuid(), connection);
    }

    void removeConnection(UUID uuid) {
        this.connections.remove(uuid);
    }

    boolean containsConnection(@NonNull UUID uuid) {
        return this.connections.containsKey(uuid);
    }

    @Override
    public boolean includes(@NonNull Node other) {
        return this.nodes.values()
                .stream()
                .anyMatch(node -> node.includes(other));
    }

    @Override
    public Set<C> getConnections() {
        return unmodifiableSet(new HashSet<>(this.connections.values()));
    }
}
