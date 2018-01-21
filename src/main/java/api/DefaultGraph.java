package api;

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
    private Map<Edge, N> inputEdgesMap;

    @NonNull
    private Map<Edge, N> outputEdgesMap;

    public DefaultGraph(@NonNull String label, N initialNode, Graph parentGraph) {
        super(label, parentGraph);
        this.initialNode = initialNode;
        this.subNodes = new HashSet<>();
        this.subNodes.add(initialNode);
        this.inputEdgesMap = new HashMap<>();
        this.outputEdgesMap = new HashMap<>();
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
    public Set<Edge> getInputEdges() {
        return inputEdgesMap.keySet();
    }

    @Override
    public Set<Edge> getOutputEdges() {
        return outputEdgesMap.keySet();
    }

    @Override
    public Map<Edge, N> getInputEdgesMap() {
        return unmodifiableMap(inputEdgesMap);
    }

    @Override
    public Map<Edge, N> getOutputEdgesMap() {
        return unmodifiableMap(outputEdgesMap);
    }

    @Override
    public Graph getParentGraph() {
        return parentGraph;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.includes(this);
    }

    void addInputEdge(Edge inputEdge, N leadTo) {
        this.inputEdgesMap.put(inputEdge, leadTo);
    }

    void addOutputEdge(Edge outputEdge, N leadTo) {
        this.outputEdgesMap.put(outputEdge, leadTo);
    }
}
