package api;

import java.util.Map;
import java.util.Set;

public interface Graph<V extends Vertex, E extends Edge> {
    V getInitialVertex();

    void setInitialVertex(V vertex);

    void createEdge(V sourceVertex, V targetVertex);

    boolean exists(V vertex);

    boolean containsEdge(V sourceVertex, V targetVertex);

    boolean containsEdge(E edge);

    boolean containsVertex(V vertex);

    E getEdgeFor(V sourceVertex, V targetVertex);

    Map<V, Set<V>> createAdjacencyList();

    Set<V> getVertices();

    Set<E> getEdges();

    void insertVertex(V vertex);
}
