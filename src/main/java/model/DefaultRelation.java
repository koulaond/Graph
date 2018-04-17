package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
class DefaultRelation extends AbstractEntity implements Relation<DefaultNode> {

    @NonNull
    private DefaultNode sourceNode;

    @NonNull
    private DefaultNode targetNode;

    @NonNull
    private Direction direction;

    public DefaultRelation(DefaultNode sourceNode, DefaultNode targetNode, Direction direction) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.direction = direction;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.containsConnection(this);
    }
}
