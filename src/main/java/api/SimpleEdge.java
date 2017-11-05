package api;

import java.util.UUID;

public class SimpleEdge<V extends Vertex> implements Edge<V> {

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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SimpleEdge)) return false;
        SimpleEdge simpleEdge = (SimpleEdge) obj;
        return (simpleEdge.left().equals(left) && simpleEdge.right().equals(right))
                || (simpleEdge.left().equals(right) && simpleEdge.right().equals(left));
    }
}
