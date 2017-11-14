package api.undirected;

import api.Edge;
import lombok.NonNull;

public interface UndirectedEdge<V extends UndirectedVertex> extends Edge<V>{

    default boolean isForVertices(@NonNull V v1, @NonNull V v2){
        return (this.getSourceVertex().equals(v1) && this.getTargetVertex().equals(v2))
                || (this.getSourceVertex().equals(v2) && this.getTargetVertex().equals(v1));
    }
}
