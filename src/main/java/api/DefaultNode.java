package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
@Setter
public class DefaultNode extends AbstractNode implements Node {

    private Set<Edge> inputEdges;

    private Set<Edge> outputEdges;

    protected DefaultNode(@NonNull String label,
                          @NonNull Graph parentGraph) {
        super(label, parentGraph);
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
