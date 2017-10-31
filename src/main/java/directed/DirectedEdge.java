package directed;

import api.Edge;
import api.Vertex;

public interface DirectedEdge<V extends DirectedVertex> extends Edge<V> {

    Direction getDirection();
}
