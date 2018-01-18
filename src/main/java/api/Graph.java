package api;

import java.util.Set;

public interface Graph extends Node {

    Set<Node> getSubNodes();

    boolean includes(Node other);
}
