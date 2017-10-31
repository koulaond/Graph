package api;

import java.util.Map;
import java.util.Set;

public class SimpleGraph<V extends Vertex<E>, E extends Edge<V>> implements Graph<V, E> {
    public V getInitialVertex() {
        return null;
    }

    public void setInitialVertex(V vertex) {

    }

    public void createEdge(V left, V right) {

    }

    public boolean exists(V vertex) {
        return false;
    }

    public boolean containsEdge(V left, V right) {
        return false;
    }

    public boolean containsEdge(E edge) {
        return false;
    }

    public boolean containsVertex(V vertex) {
        return false;
    }

    public E getEdgeFor(V left, V right) {
        return null;
    }

    public Map<V, Set<V>> createAdjacencyList() {
        return null;
    }

    public Set<V> getVertices() {
        return null;
    }

    public Set<E> getEdges() {
        return null;
    }

    public void insertVertex(V vertex) {

    }
}
