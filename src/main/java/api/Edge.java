package api;


import java.util.UUID;

public interface Edge<V extends Vertex> {
    UUID id();

    V left();

    V right();

    default boolean isForVertices(V v1, V v2){
        return (this.left().equals(v1) && this.right().equals(v2))
                || (this.left().equals(v2) && this.right().equals(v1));
    }
}
