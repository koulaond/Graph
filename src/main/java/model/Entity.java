package model;

public interface Entity extends GraphElement, HasProperties {

    Graph getParentGraph();

    boolean isIncludedIn(Graph graph);
}
