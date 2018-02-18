package model;

import java.util.Set;

public interface Node<C extends Connection> extends Entity {

    boolean includes(Node other);
}
