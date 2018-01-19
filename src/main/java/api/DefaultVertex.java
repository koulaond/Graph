package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Collections;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Getter
@Setter
public class DefaultVertex extends AbstractNode implements Vertex {

    private Set<Edge> inputEdges;

    private Set<Edge> outputEdges;

    protected DefaultVertex(@NonNull String label,
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
        if (that instanceof DefaultVertex) {
            return true;
        }
        return false;
    }
}
