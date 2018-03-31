package model;

public interface Relationship extends Entity {

    Node getSourceNode();

    Node getTargetNode();

    Direction getDirection();
}
