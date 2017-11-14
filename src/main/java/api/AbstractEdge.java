package api;

public abstract class AbstractEdge<V extends Vertex> extends AbstractGraphElement implements Edge<V> {
    protected int degree;

    protected V sourceVertex;
    protected V targetVertex;

    public V getSourceVertex() {
        return sourceVertex;
    }

    public V getTargetVertex() {
        return targetVertex;
    }

    @Override
    public int getDegree() {
        return degree;
    }

    @Override
    public  boolean isCyclic(){
        return sourceVertex.equals(targetVertex);
    }
}
