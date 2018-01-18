package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
class DefaultEdge extends AbstractItem implements Edge {

    @NonNull
    private Vertex sourceVertex;

    @NonNull
    private Vertex targetVertex;

    public DefaultEdge(String label, Vertex sourceVertex, Vertex targetVertex) {
        super(label);
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    @Override
    public boolean equals(Object that) {
        if (!super.equals(that)) {
            return false;
        }
        if (!(that instanceof DefaultEdge)) {
            return false;
        }
        DefaultEdge edge = (DefaultEdge) that;
        return this.getSourceVertex().equals(this.getSourceVertex())
                && this.getTargetVertex().equals(this.getTargetVertex());
    }
}
