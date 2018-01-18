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
    private Map<N, Edge> outputEdges;

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
    public Map<Edge, N> getInputEdges() {
        return unmodifiableMap(inputEdges);
    }

    @Override
    public Map<N, Edge> getOutputEdges() {
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

    void addInputEdge(Edge inputEdge, N leadTo){
        this.inputEdges.put(inputEdge, leadTo);
    }

    void addOutputEdge(N leadFrom, Edge outputEdge){
        this.outputEdges.put(leadFrom, outputEdge);
    }
}
