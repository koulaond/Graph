package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toSet;

public class DefaultComponent<N extends Node, C extends Relation>
        extends DefaultNode<C>
        implements Component<N, C> {

    @NonNull
    @Setter
    protected Map<UUID, N> subNodes;

    @NonNull
    @Getter
    protected Map<UUID, C> innerConnections;


    public DefaultComponent(@NonNull Graph parentGraph) {
        super(parentGraph);
        this.subNodes = new HashMap<>();
        this.innerConnections = new HashMap<>();
    }


    @Override
    public Set<N> getSubNodes() {
        return unmodifiableSet(subNodes.values().stream().collect(toSet()));
    }

    @Override
    public boolean includes(Node other) {
        return subNodes.entrySet()
                .stream()
                .map(entry -> entry.getValue())
                .anyMatch(node -> node.includes(other));
    }


    @Override
    public Graph getParentGraph() {
        return parentGraph;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.containsNode(this);
    }

    void addInnerConnection(C innerConnection){
        this.innerConnections.put(innerConnection.getUuid(),innerConnection);
    }

    void removeInnerConnection(UUID connUUID){
        this.innerConnections.remove(connUUID);
    }

    void removeInnerConnection(Relation relation){
        removeInnerConnection(relation.getUuid());
    }

    boolean containsInnerConnection(UUID uuid){
        return this.innerConnections.containsKey(uuid);
    }

    void addSubNode(N subNode){
        this.subNodes.put(subNode.getUuid(), subNode);
    }

    N getSubNode(UUID uuid){
        return subNodes.get(uuid);
    }
}
