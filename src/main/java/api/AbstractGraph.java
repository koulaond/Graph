package api;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;

/**
 * Created by Koula on 11.11.2017.
 */
public abstract class AbstractGraph<V extends Vertex<E>, E extends Edge<V>> implements Graph<V, E> {
    protected final Set<E> edges;
    protected final Set<V> vertices;
    protected V initialVertex;

    public AbstractGraph() {
        this.edges = new HashSet<>();
        this.vertices = new HashSet<>();
    }

    @Override
    public V getInitialVertex() {
        return initialVertex;
    }

    @Override
    public void setInitialVertex(V vertex) {
        validateVertices(vertex);
        initialVertex = vertex;
    }
    @Override
    public boolean containsEdge(E edge) {
        return edges.contains(edge);
    }

    @Override
    public boolean containsVertex(V vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public Set<V> getVertices() {
        return unmodifiableSet(vertices);
    }

    @Override
    public Set<E> getEdges() {
        return unmodifiableSet(edges);
    }

    @Override
    public void insertVertex(V vertex) {
        if (containsVertex(vertex)) {
            throw new IllegalStateException(format("Vertex %s is already present in the graph", vertex.id()));
        }
        vertices.add(vertex);
    }

    @Override
    public void insertVertices(V... vertices) {
        for (V vertex : vertices) insertVertex(vertex);
    }

    @Override
    public abstract void createEdge(EdgeFactory<E> edgeFactory);

    @Override
    public abstract Map<V, Set<V>> createAdjacencyList();

    @Override
    public abstract Couple<V> getVerticesForEdge(E edge);

    protected void validateVertices(V... vertices) {
        for (V vertex : vertices) {
            if (!this.vertices.contains(vertex)) {
                throw new IllegalStateException(format("Vertex %s is not present in the graph. ", vertex.id()));
            }
        }
    }
}
