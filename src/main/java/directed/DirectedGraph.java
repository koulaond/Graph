package directed;

import api.Graph;

public interface DirectedGraph<V extends DirectedVertex<E>, E extends DirectedEdge<V>> extends Graph<V, E> {

    boolean containsEdgeForVertices(V left, V right, Direction direction);

    E getEdgeFor(V left, V right, Direction direction);
}
