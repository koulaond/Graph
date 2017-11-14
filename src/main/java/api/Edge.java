package api;


public interface Edge<V extends Vertex> extends GraphElement {

    V getSourceVertex();

    V getTargetVertex();

    int getDegree();

    boolean isCyclic();
}
