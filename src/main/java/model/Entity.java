package model;

public interface Entity extends GraphElement, PropertyHolder {

    Graph getParentGraph();

    boolean isIncludedIn(Graph graph);
}
