package api;

import java.util.Map;
import java.util.Set;

public interface Graph<N extends Node, C extends Connection> extends Node<C> {

    N getInitialNode();

    Set<N> getSubNodes();

    Set<C> getInnerConnections();

    Map<C, N> getInputConnectionsMap();

    Map<C, N> getOutputConnectionsMap();

}
