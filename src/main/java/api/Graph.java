package api;

import java.util.Map;
import java.util.Set;

public interface Graph<V extends Vertex, E extends Edge> {
    V getInitialVertex();

    void setInitialVertex(V vertex);

    void createEdge(V left, V right);

    boolean exists(V vertex);

    boolean containsEdge(V left, V right);

    boolean containsEdge(E edge);

    boolean containsVertex(V vertex);

    E getEdgeFor(V left, V right);

    Map<V, Set<V>> createAdjacencyList();

    Set<V> getVertices();

    Set<E> getEdges();

    void insertVertex(V vertex);
}
