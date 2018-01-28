package api;

import java.util.Set;

public interface Graph<N extends Node, C extends Connection> extends GraphElement {

    N getInitialNode();

    Set<C> getConnections();

    Set<N> getNodes();

    boolean includes(Node other);
}
