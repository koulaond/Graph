package directed;

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
        validateVertices(edge.getSourceVertex(), edge.getTargetVertex());
        edges.add(edge);
        List<V> verticesForEdge = getVerticesForEdge(edge);

        if((edge.getDirection().equals(Direction.FORWARD) &&verticesForEdge.get(0).equals(edge.getSourceVertex()))
                || (edge.getDirection().equals(Direction.BACKWARD) && verticesForEdge.get(1).equals(edge.getTargetVertex()))){
            verticesForEdge.get(0).addOutcomeEdge(edge);
            verticesForEdge.get(1).addIncomeEdge(edge);
            edges.add(edge);

        } else if((edge.getDirection().equals(Direction.FORWARD) && verticesForEdge.get(1).equals(edge.getSourceVertex()))
                || (edge.getDirection().equals(Direction.BACKWARD) && verticesForEdge.get(0).equals(edge.getSourceVertex()))){
            verticesForEdge.get(0).addIncomeEdge(edge);
            verticesForEdge.get(1).addOutcomeEdge(edge);
            edges.add(edge);

        } else {
            throw new IllegalStateException("There is inconsistency between edges and vertices");
        }
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
