package api.directed;

import api.AbstractGraph;
import api.EdgeFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        return null;
    }

    @Override
    public List<V> getVerticesForEdge(E edge) {
        return null;
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
