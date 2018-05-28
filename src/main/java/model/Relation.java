package model;


import java.util.Map;

public class Relation extends GraphEntity {

    private Node sourceNode;

    private Node targetNode;

    private boolean directed;

    public Relation(Long id, Map<String,
            Object> properties,
                    Graph parentGraph,
                    Node sourceNode,
                    Node targetNode,
                    boolean directed) {

        super(id, properties, parentGraph);
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.directed = directed;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public boolean isDirected() {
        return directed;
    }
}
