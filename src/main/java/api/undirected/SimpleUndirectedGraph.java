package api.undirected;

import api.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;

public class SimpleUndirectedGraph<V extends UndirectedVertex<E>, E extends UndirectedEdge<V>>
        extends AbstractGraph<V, E> implements UndirectedGraph<V, E> {

    public void createEdge(EdgeFactory<E> edgeFactory) {
        E edge = edgeFactory.create();
        validateVertices(edge.getSourceVertex(), edge.getTargetVertex());
        edges.add(edge);
        vertices.stream()
                .filter(vertex -> vertex.equals(edge.getSourceVertex()) || vertex.equals(edge.getTargetVertex()))
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
                        new IllegalStateException(
                                format(
                                        "No edge found for vertices %s, %s",
                                        left.getId(),
                                        right.getId()
                                )
                        ));
    }

    public Map<V, Set<V>> createAdjacencyList() {

        return vertices.stream()
                .collect(
                        toMap(
                                Function.identity(),
                                vertex -> vertex.getAllEdges().stream()
                                        .map(edge -> {
                                            if (edge.getSourceVertex().equals(vertex)
                                                    && edge.getTargetVertex().equals(vertex))
                                                return vertex;
                                            else if (edge.getSourceVertex().equals(vertex))
                                                return edge.getTargetVertex();
                                            else return edge.getSourceVertex();
                                        })
                                        .collect(Collectors.toSet())
                        )
                );
    }
}
