package directed;

import api.AbstractGraph;
import api.Couple;
import api.EdgeFactory;

import java.util.Map;
import java.util.Set;

public class SimpleDirectedGraph<V extends DirectedVertex<E>, E extends DirectedEdge<V>>
        extends AbstractGraph<V, E>
        implements DirectedGraph<V, E> {

    @Override
    public void createEdge(EdgeFactory<E> edgeFactory) {
        E edge = edgeFactory.create();
        validateVertices(edge.getSource(), edge.getTarget());
        edges.add(edge);
        Couple<V> verticesForEdge = getVerticesForEdge(edge);

        if((edge.getDirection().equals(Direction.FORWARD) &&verticesForEdge.v1().equals(edge.getSource()))
                || (edge.getDirection().equals(Direction.BACKWARD) && verticesForEdge.v2().equals(edge.getTarget()))){
            verticesForEdge.v1().addOutcomeEdge(edge);
            verticesForEdge.v2().addIncomeEdge(edge);
            edges.add(edge);

        } else if((edge.getDirection().equals(Direction.FORWARD) && verticesForEdge.v2().equals(edge.getSource()))
                || (edge.getDirection().equals(Direction.BACKWARD) && verticesForEdge.v1().equals(edge.getSource()))){
            verticesForEdge.v1().addIncomeEdge(edge);
            verticesForEdge.v2().addOutcomeEdge(edge);
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
    public Couple<V> getVerticesForEdge(E edge) {
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
