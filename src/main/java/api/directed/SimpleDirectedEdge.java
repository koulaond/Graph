package api.directed;

import api.AbstractEdge;

import java.util.Map;

public class SimpleDirectedEdge<V extends DirectedVertex>
        extends AbstractEdge<V>
        implements DirectedEdge<V> {

    protected final Direction direction;

    public SimpleDirectedEdge(String label,
                              Map<String, Object> properties,
                              int degree,
                              V sourceVertex,
                              V targetVertex,
                              Direction direction) {

        super(label, properties, degree, sourceVertex, targetVertex);
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof SimpleDirectedEdge)){
            return false;
        }
        return getDirection().equals(((SimpleDirectedEdge) obj).getDirection());
    }

    public static SimpleUndirectedEdgeBuilder<? extends DirectedVertex> builder(){
        return new SimpleUndirectedEdgeBuilder<>();
    }

    private static class SimpleUndirectedEdgeBuilder<V extends DirectedVertex> extends AbstractEdgeBuilder<V> {

        protected  Direction direction;

        public SimpleUndirectedEdgeBuilder direction(Direction direction){
            this.direction = direction;
            return this;
        }

        public SimpleDirectedEdge<V> build() {
            return new SimpleDirectedEdge<>(
                    this.label,
                    this.properties,
                    this.degree,
                    this.sourceVertex,
                    this.targetVertex,
                    this.direction);
        }
    }


}
