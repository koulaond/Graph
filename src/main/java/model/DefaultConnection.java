package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
class DefaultConnection extends AbstractEntity implements Connection {

    @NonNull
    private Node sourceNode;

    @NonNull
    private Node targetNode;

    public DefaultConnection(String label, Node sourceNode, Node targetNode) {
        super(label);
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.containsConnection(this);
    }
}
