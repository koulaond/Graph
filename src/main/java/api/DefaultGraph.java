package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toSet;

public class DefaultGraph<N extends Node, C extends Connection>
        extends DefaultNode<C>
        implements Graph<N, C> {

    @NonNull
    private N initialNode;

    @NonNull
    @Setter
    private Map<UUID, N> subNodes;

    @NonNull
    @Getter
    private Map<UUID, C> innerConnections;

    @NonNull
    private Map<C, N> inputConnectionsMap;

    @NonNull
    private Map<C, N> outputConnectionsMap;

    public DefaultGraph(@NonNull String label, N initialNode, Graph parentGraph) {
        super(label, parentGraph);
        this.initialNode = initialNode;
        this.subNodes = new HashMap<>();
        this.subNodes.put(initialNode.getUuid(), initialNode);
        this.innerConnections = new HashMap<>();
        this.inputConnectionsMap = new HashMap<>();
        this.outputConnectionsMap = new HashMap<>();
    }

    public DefaultGraph(@NonNull String label, N initialNode) {
        this(label, initialNode, null);
    }

    @Override
    public N getInitialNode() {
        return initialNode;
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
    public Set<C> getInputConnections() {
        return inputConnectionsMap.keySet();
    }

    @Override
    public Set<C> getOutputConnections() {
        return outputConnectionsMap.keySet();
    }

    @Override
    public Map<C, N> getInputConnectionsMap() {
        return unmodifiableMap(inputConnectionsMap);
    }

    @Override
    public Map<C, N> getOutputConnectionsMap() {
        return unmodifiableMap(outputConnectionsMap);
    }

    @Override
    public Graph getParentGraph() {
        return parentGraph;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.includes(this);
    }

    void addInputEdge(C inputConnection, N leadTo) {
        this.inputConnectionsMap.put(inputConnection, leadTo);
    }

    void addOutputEdge(C outputConnection, N leadTo) {
        this.outputConnectionsMap.put(outputConnection, leadTo);
    }

    void addInnerConnection(C innerConnection){
        this.innerConnections.put(innerConnection.getUuid(),innerConnection);
    }

    void removeInnerConnection(UUID connUUID){
        this.innerConnections.remove(connUUID);
    }

    void removeInnerConnection(Connection connection){
        removeInnerConnection(connection.getUuid());
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
