package api;

import java.util.UUID;

public class SimpleEdge<V extends SimpleVertex> implements Edge<V> {

    private final UUID id;
    private final V left;
    private final V right;


    public SimpleEdge(V left, V right) {
        this.id = UUID.randomUUID();
        this.left = left;
        this.right = right;
    }

    @Override
    public UUID id() {
        return id;
    }

    public V left() {
        return left;
    }

    public V right() {
        return right;
    }
}
