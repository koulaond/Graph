package model;

import java.util.Set;

public interface Node<C extends Connection> extends GraphElement {

    Graph getParentGraph();

    boolean isIncludedIn(Graph graph);

    boolean includes(Node other);

    Set<C> getInputConnections();

    Set<C> getOutputConnections();
}
