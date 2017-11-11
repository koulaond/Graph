package api.undirected;

import java.util.UUID;

public class SimpleUndirectedEdge<V extends UndirectedVertex> implements UndirectedEdge<V> {

    protected final UUID id;
    protected final V v1;
    protected final V v2;

    public SimpleUndirectedEdge(V v1, V v2) {
        this.id = UUID.randomUUID();
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public UUID id() {
        return id;
    }

    public V v1() {
        return v1;
    }

    public V v2() {
        return v2;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof SimpleUndirectedEdge)) return false;
        SimpleUndirectedEdge simpleUndirectedEdge = (SimpleUndirectedEdge) obj;
        return (simpleUndirectedEdge.v1().equals(v1) && simpleUndirectedEdge.v2().equals(v2))
                || (simpleUndirectedEdge.v1().equals(v2) && simpleUndirectedEdge.v2().equals(v1));
    }
}
