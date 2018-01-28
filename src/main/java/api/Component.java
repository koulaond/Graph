package api;

import java.util.Map;
import java.util.Set;

public interface Component<N extends Node, C extends Connection> extends Node<C> {

    Set<N> getSubNodes();

    Set<C> getInnerConnections();

}
