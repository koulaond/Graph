package api.directed;

import api.AbstractGraph;
import api.EdgeFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

public class SimpleDirectedGraph<V extends DirectedVertex<E>, E extends DirectedEdge<V>>
        extends AbstractGraph<V, E>
        implements DirectedGraph<V, E> {

    @Override
    public void createEdge(EdgeFactory<E> edgeFactory) {
        E edge = edgeFactory.create();
        V sourceVertex = edge.getSourceVertex();
        V targetVertex = edge.getTargetVertex();
        validateVertices(sourceVertex, targetVertex);
        edges.add(edge);

    }

    @Override
    public Map<V, Set<V>> createAdjacencyList() {
        return getVertices().stream()
                .collect(
                        toMap(
                                Function.identity(),
                                vertex -> vertex.getOutcomeEdges()
                                        .stream()
                                        .flatMap(edge -> asList(edge.getSourceVertex(), edge.getTargetVertex()).stream())
                                        .filter(edgeVertex -> !edgeVertex.equals(vertex))
                                        .collect(Collectors.toSet())
                        )
                );
    }

    @Override
    public boolean containsEdgeForVertices(V left, V right, Direction direction) {
        return getEdgeForVertices(left, right, direction) != null;
    }

    @Override
    public E getEdgeForVertices(V left, V right, Direction direction) {
        return edges.stream()
                .filter(edge -> edge.isForVertices(left, right, direction))
                .findFirst()
                .orElse(null);
    }
}
