package api;

public interface Component<G extends Graph> extends Node {

    G getParentGraph();
}
