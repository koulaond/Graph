package directed;

import api.Vertex;

import java.util.Set;

public interface DirectedVertex<E extends DirectedEdge> extends Vertex<E> {

    Set<E> getIncomeEdges();

    Set<E> getOutcomeEdges();
}
