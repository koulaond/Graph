package api;

public interface Node extends GraphElement {

    Graph getParentGraph();

    boolean isIncludedIn(Graph graph);

    boolean includes(Node other);
}
