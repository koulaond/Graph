package api;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;

public class SimpleGraph<V extends Vertex<E>, E extends Edge<V>> implements Graph<V, E> {
    private final Set<E> edges;
    private final Set<V> vertices;
    private V initialVertex;

    public SimpleGraph() {
        this.edges = new HashSet<>();
        this.vertices = new HashSet<>();
    }

    public V getInitialVertex() {
        return initialVertex;
    }

    public void setInitialVertex(V vertex) {
        validateVertices(vertex);
        initialVertex = vertex;
    }

    public void createEdge(EdgeFactory<E> edgeFactory) {
        E edge = edgeFactory.create();
        validateVertices(edge.left(), edge.right());
        edges.add(edge);
        vertices.stream()
                .filter(vertex -> vertex.equals(edge.left()))
                .filter(vertex -> vertex.equals(edge.right()))
                .forEach(vertex -> vertex.addEdge(edge));
    }

    public boolean containsEdgeForVertices(V left, V right) {
        return edges.stream()
                .anyMatch(edge -> edge.isForVertices(left, right));
    }

    public boolean containsEdge(E edge) {
        return edges.contains(edge);
    }

    public boolean containsVertex(V vertex) {
        return vertices.contains(vertex);
    }

    public E getEdgeFor(V left, V right) {
        return edges.stream()
                .filter(edge -> edge.isForVertices(left, right))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(format("No edge found for vertices %s, %s", left.id(), right.id())));
    }

    public Map<V, Set<V>> createAdjacencyList() {

        return null;
    }

    public Set<V> getVertices() {
        return unmodifiableSet(vertices);
    }

    public Set<E> getEdges() {
        return unmodifiableSet(edges);
    }

    public void insertVertex(V vertex) {
        if(vertices.contains(vertex)){
            throw new IllegalStateException(format("Vertex %s is already present in the graph", vertex.id()));
        }
        vertices.add(vertex);
    }

    private void validateVertices(V... vertices) {
        for (V vertex : vertices) {
            if (!this.vertices.contains(vertex)) {
                throw new IllegalStateException(format("Vertex %s is not present in the graph. ", vertex.id()));
            }
        }
    }
}
