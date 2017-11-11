package directed;

import java.util.UUID;

public class SimpleDirectedEdge<V extends DirectedVertex> implements DirectedEdge<V> {

    protected UUID id;
    protected final V source, target;
    protected final Direction direction;

    public SimpleDirectedEdge(V source, V target, Direction direction) {
        this.id = UUID.randomUUID();
        this.source = source;
        this.target = target;
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public UUID id() {
        return id;
    }

    @Override
    public V getSource() {
        return source;
    }

    @Override
    public V getTarget() {
        return target;
    }
}
