package api;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Graph<V extends Vertex, E> {
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
}
