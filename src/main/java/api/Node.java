package api;

import java.util.Set;

public interface Node extends GraphElement {

    Graph getParentGraph();

    boolean isIncludedIn(Graph graph);

    boolean includes(Node other);

    Set<Edge> getInputEdges();

    Set<Edge> getOutputEdges();
}
