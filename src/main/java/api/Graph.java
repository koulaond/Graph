package api;

import java.util.Map;
import java.util.Set;

public interface Graph<N extends Node> extends Node {

    N getInitialNode();

    Set<N> getSubNodes();

    Set<Connection> getInnerConnections();

    Map<Connection, N> getInputConnectionsMap();

    Map<Connection, N> getOutputConnectionsMap();

}
