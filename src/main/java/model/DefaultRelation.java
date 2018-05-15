package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


class DefaultRelation extends AbstractEntity implements Relation<DefaultNode> {

    private DefaultNode sourceNode;

    private DefaultNode targetNode;

    private Direction direction;

    public DefaultRelation(DefaultNode sourceNode, DefaultNode targetNode, Direction direction) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.direction = direction;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return graph.containsRelation(this);
    }

    @Override
    public DefaultNode getSourceNode() {
        return null;
    }

    @Override
    public DefaultNode getTargetNode() {
        return null;
    }

    @Override
    public Direction getDirection() {
        return null;
    }
}
