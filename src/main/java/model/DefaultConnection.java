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

    @NonNull
    private Direction direction;

    public DefaultConnection(Node sourceNode, Node targetNode, Direction direction) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.direction = direction;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.containsConnection(this);
    }
}
