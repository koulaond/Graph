package api.undirected;

import api.Edge;
import lombok.NonNull;

public interface UndirectedEdge<V extends UndirectedVertex> extends Edge<V> {

    V v1();

    V v2();

    default boolean isForVertices(@NonNull V v1, @NonNull V v2){
        return (this.v1().equals(v1) && this.v2().equals(v2))
                || (this.v1().equals(v2) && this.v2().equals(v1));
    }
}
