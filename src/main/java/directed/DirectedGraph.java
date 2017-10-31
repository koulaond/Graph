package directed;

import api.Graph;

public interface DirectedGraph<V extends DirectedVertex<E>, E extends DirectedEdge<V>> extends Graph<V, E> {

    void createEdge(V left, V right, Direction direction);

    boolean containsEdge(V left, V right, Direction direction);

    E getEdgeFor(V left, V right, Direction direction);
}
