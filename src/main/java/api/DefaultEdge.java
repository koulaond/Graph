package api;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
class DefaultEdge extends AbstractItem implements Edge {

    @NonNull
    private Node sourceNode;

    @NonNull
    private Node targetNode;

    public DefaultEdge(String label, Node sourceNode, Node targetNode) {
        super(label);
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
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
        return this.getSourceNode().equals(edge.getSourceNode())
                && this.getTargetNode().equals(edge.getTargetNode());
    }
}
