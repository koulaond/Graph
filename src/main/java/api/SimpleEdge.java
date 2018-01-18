package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class SimpleEdge extends AbstractItem implements Edge {

    @NonNull
    private Vertex sourceVertex;

    @NonNull
    private Vertex targetVertex;

    public SimpleEdge(String label, Map<String, Object> properties, Vertex sourceVertex, Vertex targetVertex) {
        super(label, properties);
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (!(that instanceof SimpleEdge)) {
            return false;
        }
        SimpleEdge edge = (SimpleEdge) that;
        return this.getSourceVertex().equals(this.getSourceVertex())
                && this.getTargetVertex().equals(this.getTargetVertex());
    }
}
