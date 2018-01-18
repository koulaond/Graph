package api;

public interface Edge extends GraphElement {

    Vertex getSourceVertex();

    Vertex getTargetVertex();

}
