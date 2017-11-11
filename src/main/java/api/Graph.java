package api;

import java.util.Map;
import java.util.Set;

public interface Graph<V extends Vertex, E extends Edge> {
    V getInitialVertex();

    void setInitialVertex(V vertex);

    void createEdge(EdgeFactory<E> edgeFactory);

    boolean containsEdge(E edge);

    boolean containsVertex(V vertex);

    Map<V, Set<V>> createAdjacencyList();

    Set<V> getVertices();

    Set<E> getEdges();

    void insertVertex(V vertex);

    void insertVertices(V... vertices);

    Couple<V> getVerticesForEdge(E edge);
}
