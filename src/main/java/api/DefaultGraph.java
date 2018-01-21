package api;

import lombok.NonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.unmodifiableSet;

public class DefaultGraph<N extends Node> extends AbstractItem implements Graph<N> {

    private Graph parentGraph;

    @NonNull
    private N initialNode;

    @NonNull
    private Set<N> subNodes;

    @NonNull
    private Map<Edge, N> inputEdges;

    @NonNull
    private Map<Edge, N> outputEdges;

    public DefaultGraph(@NonNull String label, N initialNode) {
        super(label);
        this.initialNode = initialNode;
        this.subNodes = new HashSet<>();
        this.subNodes.add(initialNode);
        this.inputEdges = new HashMap<>();
        this.outputEdges = new HashMap<>();
    }

    public DefaultGraph(@NonNull String label, N initialNode, Graph parentGraph) {
        this(label, initialNode);
        this.parentGraph = parentGraph;
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
        return inputEdges.keySet();
    }

    @Override
    public Set<Edge> getOutputEdges() {
        return outputEdges.keySet();
    }

    @Override
    public Map<Edge, N> getInputEdgesMap() {
        return unmodifiableMap(inputEdges);
    }

    @Override
    public Map<Edge, N> getOutputEdgesMap() {
        return unmodifiableMap(outputEdges);
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
        this.inputEdges.put(inputEdge, leadTo);
    }

    void addOutputEdge(Edge outputEdge, N leadTo) {
        this.outputEdges.put(outputEdge, leadTo);
    }
}
