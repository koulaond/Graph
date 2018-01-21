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

    private Set<Connection> inputConnections;

    private Set<Connection> outputConnections;

    protected DefaultNode(@NonNull String label,
                          @NonNull Graph parentGraph) {
        super(label);
        this.parentGraph = parentGraph;
        this.inputConnections = new HashSet<>();
        this.outputConnections = new HashSet<>();
    }

    @Override
    public Set<Connection> getInputConnections() {
        return unmodifiableSet(inputConnections);
    }

    @Override
    public Set<Connection> getOutputConnections() {
        return unmodifiableSet(outputConnections);
    }

    @Override
    public boolean includes(Node other) {
        return this.equals(other);
    }

    @Override
    public boolean isIncludedIn(@NonNull Graph graph) {
        return graph.includes(this);
    }

    void addInputEdge(Connection inputConnection){
        this.inputConnections.add(inputConnection);
    }

    void addOutputEdge(Connection outputConnection){
        this.outputConnections.add(outputConnection);
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
