package model;

public interface Node<C extends Relationship> extends Entity {

    boolean includes(Node other);
}
