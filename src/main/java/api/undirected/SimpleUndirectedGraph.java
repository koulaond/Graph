package api.undirected;

import api.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toMap;

public class SimpleUndirectedGraph<V extends UndirectedVertex<E>, E extends UndirectedEdge<V>>
        extends AbstractGraph<V, E> implements UndirectedGraph<V, E> {

    protected final Set<E> edges;
    protected final Set<V> vertices;
    protected V initialVertex;

    public SimpleUndirectedGraph() {
        this.edges = new HashSet<>();
        this.vertices = new HashSet<>();
    }

    public void createEdge(EdgeFactory<E> edgeFactory) {
        E edge = edgeFactory.create();
        validateVertices(edge.v1(), edge.v2());
        edges.add(edge);
        vertices.stream()
                .filter(vertex -> vertex.equals(edge.v1()) || vertex.equals(edge.v2()))
                .forEach(vertex -> vertex.addEdge(edge));
    }

    public boolean containsEdgeForVertices(V left, V right) {
        return edges.stream()
                .anyMatch(edge -> edge.isForVertices(left, right));
    }

    public E getEdgeFor(V left, V right) {
        return edges.stream()
                .filter(edge -> edge.isForVertices(left, right))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(format("No edge found for vertices %s, %s", left.id(), right.id())));
    }

    public Map<V, Set<V>> createAdjacencyList() {
        return vertices.stream()
                .collect(
                        toMap(
                                Function.identity(),
                                vertex -> vertex.getAllEdges().stream()
                                        .map(edge -> {
                                            if (edge.v1().equals(vertex) && edge.v2().equals(vertex))
                                                return vertex;
                                            else if (edge.v1().equals(vertex)) return edge.v2();
                                            else return edge.v1();
                                        })
                                        .collect(Collectors.toSet())
                        )
                );
    }

    @Override
    public Couple<V> getVerticesForEdge(E edge) {
        List<V> foundVertices = this.vertices.stream()
                .filter(vertex -> vertex.equals(edge.v1()) || vertex.equals(edge.v2()))
                .collect(Collectors.toList());
        if (foundVertices.size() == 0) {
            return null;
        }
        if (foundVertices.size() != 2) {
            throw new IllegalStateException("There must be two or zero vertices for the given edge.");
        }
        return new Couple<V>(foundVertices.get(0), foundVertices.get(1));
    }
}
