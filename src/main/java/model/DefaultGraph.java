package model;

import lombok.NonNull;

import java.util.*;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;

public class DefaultGraph extends AbstractItem implements Graph {

    private Node initialNode;

    private Map<UUID, Node> nodes;

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

    void setInitialNode(Node initialNode) {
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

    Node getNode(UUID uuid) {
        return this.nodes.get(uuid);
    }

    void addNode(Node node) {
        this.nodes.put(node.getUuid(), node);
    }

    void removeNode(UUID uuid) {
        this.nodes.remove(uuid);
    }

    boolean containsNode(UUID uuid) {
        return this.nodes.containsKey(uuid);
    }

    Relation getConnection(UUID uuid) {
        return this.relations.get(uuid);
    }

    void addConnection(Relation relation) {
        this.relations.put(relation.getUuid(), relation);
    }

    void removeConnection(UUID uuid) {
        this.relations.remove(uuid);
    }

    boolean containsConnection(UUID uuid) {
        return this.relations.containsKey(uuid);
    }

    @Override
    public boolean containsNode(Node that) {
        return this.nodes.values()
                .stream()
                .anyMatch(node -> node.includes(that));
    }

    @Override
    public boolean containsRelation(Relation that) {
        return this.relations.values()
                .stream()
                .anyMatch(connection -> connection.equals(that));
    }

    @Override
    public Set<Relation> getRelations() {
        return unmodifiableSet(new HashSet<>(this.relations.values()));
    }
}
