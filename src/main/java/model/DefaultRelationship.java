package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
class DefaultRelationship extends AbstractEntity implements Relationship {

    @NonNull
    private Node sourceNode;

    @NonNull
    private Node targetNode;

    @NonNull
    private Direction direction;

    public DefaultRelationship(Node sourceNode, Node targetNode, Direction direction) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.direction = direction;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.containsConnection(this);
    }
}
