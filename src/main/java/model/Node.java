package model;

public interface Node<C extends Relation> extends Entity {

    boolean includes(Node other);
}
