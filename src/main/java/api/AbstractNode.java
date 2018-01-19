package api;

import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

public abstract class AbstractNode extends AbstractItem implements Node {

    @Getter
    private Graph parentGraph;

    protected AbstractNode(@NonNull String label,
                           Graph parentGraph) {
        super(label);
        this.parentGraph = parentGraph;
    }

    @Override
    public boolean isIncludedIn(@NonNull Graph graph) {
        return graph.includes(this);
    }
}
