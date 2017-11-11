package directed;

import api.Edge;
import api.Vertex;
import lombok.NonNull;

public interface DirectedEdge<V extends DirectedVertex> extends Edge<V> {

    Direction getDirection();

    V getSource();

    V getTarget();

    default boolean isForVertices(@NonNull V source, @NonNull V target, @NonNull Direction direction) {
        return this.getSource().equals(source)
                && this.getTarget().equals(target)
                && this.getDirection().equals(direction);
    }
}
