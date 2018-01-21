package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

public class DefaultGraph<N extends Node> extends DefaultNode implements Graph<N> {

    @NonNull
    private N initialNode;

    @NonNull
    @Setter
    private Set<N> subNodes;

    @NonNull
    @Getter
    private Set<Connection> innerConnections;

    @NonNull
    private Map<Connection, N> inputConnectionsMap;

    @NonNull
    private Map<Connection, N> outputConnectionsMap;

    public DefaultGraph(@NonNull String label, N initialNode, Graph parentGraph) {
        super(label, parentGraph);
        this.initialNode = initialNode;
        this.subNodes = new HashSet<>();
        this.subNodes.add(initialNode);
        this.innerConnections = new HashSet<>();
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
        return unmodifiableSet(subNodes);
    }

    @Override
    public boolean includes(Node other) {
        return subNodes.stream().anyMatch(node -> node.includes(other));
    }

    @Override
    public Set<Connection> getInputConnections() {
        return inputConnectionsMap.keySet();
    }

    @Override
    public Set<Connection> getOutputConnections() {
        return outputConnectionsMap.keySet();
    }

    @Override
    public Map<Connection, N> getInputConnectionsMap() {
        return unmodifiableMap(inputConnectionsMap);
    }

    @Override
    public Map<Connection, N> getOutputConnectionsMap() {
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

    void addInputEdge(Connection inputConnection, N leadTo) {
        this.inputConnectionsMap.put(inputConnection, leadTo);
    }

    void addOutputEdge(Connection outputConnection, N leadTo) {
        this.outputConnectionsMap.put(outputConnection, leadTo);
    }

    void addInnerConnection(Connection innerConnection){
        this.innerConnections.add(innerConnection);
    }
}
