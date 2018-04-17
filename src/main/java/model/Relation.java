package model;

public interface Relation<N> extends Entity {

    N getSourceNode();

    N getTargetNode();

    Direction getDirection();
}
