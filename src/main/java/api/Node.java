package api;

public interface Node {

    Graph getParentGraph();

    boolean isIncludedIn(Graph graph);

}
