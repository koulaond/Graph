package api.directed;

import api.AbstractGraphElement;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimpleDirectedVertex<E extends DirectedEdge> extends AbstractGraphElement implements DirectedVertex<E> {

    private final Set<E> incomeEdges, outcomeEdges;

    public SimpleDirectedVertex(String label, Map<String, Object> properties) {
        super(label, properties);
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
        incomeEdges.add(e);
    }

    @Override
    public void addOutcomeEdge(E e) {
        outcomeEdges.add(e);
    }

    @Override
    public Set<E> getAllEdges() {
        // TODO try to optimize
        Set<E> allEdges = new HashSet<E>();
        allEdges.addAll(incomeEdges);
        allEdges.addAll(outcomeEdges);
        return allEdges;
    }

    protected static class SimpleDirectedVertexBuilder<E extends DirectedEdge> extends AbstractGraphElementBuilder{

        public SimpleDirectedVertex<E> build(){
            return new SimpleDirectedVertex<>(this.label, this.properties);
        }
    }
}
