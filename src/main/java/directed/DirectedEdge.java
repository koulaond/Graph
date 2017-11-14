package directed;

import api.Edge;
import lombok.NonNull;

public interface DirectedEdge<V extends DirectedVertex> extends Edge<V> {

    Direction getDirection();

    default boolean isForVertices(@NonNull V source, @NonNull V target, @NonNull Direction direction) {
        return this.getSourceVertex().equals(source)
                && this.getTargetVertex().equals(target)
                && this.getDirection().equals(direction);
    }
}
