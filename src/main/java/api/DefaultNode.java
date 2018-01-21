package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
@Setter
public class DefaultNode extends AbstractItem implements Node {

    @Getter
    protected Graph parentGraph;

    private Set<Edge> inputEdges;

    private Set<Edge> outputEdges;

    protected DefaultNode(@NonNull String label,
                          @NonNull Graph parentGraph) {
        super(label);
        this.parentGraph = parentGraph;
        this.inputEdges = new HashSet<>();
        this.outputEdges = new HashSet<>();
    }

    @Override
    public Set<Edge> getInputEdges() {
        return unmodifiableSet(inputEdges);
    }

    @Override
    public Set<Edge> getOutputEdges() {
        return unmodifiableSet(outputEdges);
    }

    @Override
    public boolean includes(Node other) {
        return this.equals(other);
    }

    @Override
    public boolean isIncludedIn(@NonNull Graph graph) {
        return graph.includes(this);
    }

    void addInputEdge(Edge inputEdge){
        this.inputEdges.add(inputEdge);
    }

    void addOutputEdge(Edge outputEdge){
        this.outputEdges.add(outputEdge);
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (that instanceof DefaultNode) {
            return true;
        }
        return false;
    }
}
