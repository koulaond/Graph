package api;

import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

public class AbstractNode extends AbstractItem implements Node {

    @Getter
    private Graph parentGraph;

    protected AbstractNode(@NonNull String label,
                           @NonNull Map<String, Object> properties,
                           Graph parentGraph) {
        super(label, properties);
        this.parentGraph = parentGraph;
    }

    @Override
    public boolean isIncludedIn(@NonNull Graph graph) {
        return graph.includes(this);
    }
}
