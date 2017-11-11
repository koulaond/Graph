package api.undirected;

import api.Graph;

public interface UndirectedGraph<V extends UndirectedVertex<E>, E extends UndirectedEdge<V>> extends Graph<V, E> {

    boolean containsEdgeForVertices(V left, V right);

    E getEdgeFor(V left, V right);
}
