package directed;

import api.AbstractEdge;

public class SimpleDirectedEdge<V extends DirectedVertex>
        extends AbstractEdge<V>
        implements DirectedEdge<V> {

    protected final Direction direction;

    public SimpleDirectedEdge(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

}
