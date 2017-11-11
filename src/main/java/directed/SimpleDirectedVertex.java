package directed;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SimpleDirectedVertex<E extends DirectedEdge> implements DirectedVertex<E> {

    private final UUID id;
    private final Set<E> incomeEdges, outcomeEdges;

    public SimpleDirectedVertex() {
        this.id = UUID.randomUUID();
        this.incomeEdges = new HashSet<>();
        this.outcomeEdges = new HashSet<>();
    }

    @Override
    public Set<E> getIncomeEdges() {
        return incomeEdges;
    }

    @Override
    public Set<E> getOutcomeEdges() {
        return outcomeEdges;
    }

    @Override
    public void addIncomeEdge(E e) {

    }

    @Override
    public void addOutcomeEdge(E e) {

    }

    @Override
    public UUID id() {
        return null;
    }

    @Override
    public Set<E> getAllEdges() {
        return null;
    }
}
