package api;

import java.util.Map;

public abstract class AbstractEdge<V extends Vertex> extends AbstractGraphElement implements Edge<V> {

    protected int degree;
    protected V sourceVertex;
    protected V targetVertex;

    protected AbstractEdge(String label, Map<String, Object> properties, int degree, V sourceVertex, V targetVertex) {
        super(label, properties);
        this.degree = degree;
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

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
    public boolean isCyclic() {
        return sourceVertex.equals(targetVertex);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof AbstractEdge)) {
            return false;
        }
        AbstractEdge that = (AbstractEdge) obj;
        return !(getDegree() != that.getDegree()
                || !getSourceVertex().equals(that.getSourceVertex())
                || !getTargetVertex().equals(that.getTargetVertex()));

    }

    protected static abstract class AbstractEdgeBuilder<V extends Vertex> extends AbstractGraphElementBuilder {

        protected int degree;
        protected V sourceVertex;
        protected V targetVertex;

        public AbstractEdgeBuilder degree(int degree) {
            this.degree = degree;
            return this;
        }

        public AbstractEdgeBuilder sourceTarget(V sourceVertex) {
            this.sourceVertex = sourceVertex;
            return this;
        }

        public AbstractEdgeBuilder targetVertex(V targetVertex) {
            this.targetVertex = targetVertex;
            return this;
        }
    }
}
