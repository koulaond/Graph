package api;

/**
 * Simple wrapper that contains two vertices connected by an edge
 * @param <V>
 * @param <E>
 */
public class Couple<V extends Vertex> {
    private final V v1;
    private final V v2;

    public Couple(V v1, V v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public V v1() {
        return v1;
    }

    public V v2() {
        return v2;
    }
}
