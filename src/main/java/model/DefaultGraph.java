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
    private Map<UUID, Relation> relations;

    protected DefaultGraph(Node initialNode) {
        this.initialNode = initialNode;
        this.nodes = new HashMap<>();
        this.relations = new HashMap<>();
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

    Relation getConnection(@NonNull UUID uuid) {
        return this.relations.get(uuid);
    }

    void addConnection(@NonNull Relation relation) {
        this.relations.put(relation.getUuid(), relation);
    }

    void removeConnection(UUID uuid) {
        this.relations.remove(uuid);
    }

    boolean containsConnection(@NonNull UUID uuid) {
        return this.relations.containsKey(uuid);
    }

    @Override
    public boolean containsNode(@NonNull Node that) {
        return this.nodes.values()
                .stream()
                .anyMatch(node -> node.includes(that));
    }

    @Override
    public boolean containsRelation(@NonNull Relation that) {
        return this.relations.values()
                .stream()
                .anyMatch(connection -> connection.equals(that));
    }

    @Override
    public Set<Relation> getRelations() {
        return unmodifiableSet(new HashSet<>(this.relations.values()));
    }
}
