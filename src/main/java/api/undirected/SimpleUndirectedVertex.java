package api.undirected;

import api.AbstractGraphElement;
import java.util.*;

public class SimpleUndirectedVertex<E extends UndirectedEdge>
        extends AbstractGraphElement
        implements UndirectedVertex<E> {

    protected final Set<E> edges;

    public SimpleUndirectedVertex(String label, Map<String, Object> properties) {
        super(label, properties);
        this.edges = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public Set<E> getAllEdges() {
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public boolean addEdge(E edge) {
        if (edges.contains(edge)) return false;
        edges.add(edge);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) return true;
        if (obj == null) return false;
        if (!(obj instanceof SimpleUndirectedVertex)) return false;
        return this.getId().equals(((SimpleUndirectedVertex) obj).getId());
    }

    public static SimpleUndirectedVertexBuilder<? extends UndirectedEdge> builder(){
        return new SimpleUndirectedVertexBuilder<>();
    }

    protected static class SimpleUndirectedVertexBuilder<E extends UndirectedEdge>
            extends AbstractGraphElementBuilder {

        public SimpleUndirectedVertex<E> build() {
            return new SimpleUndirectedVertex<E>(this.label, this.properties);
        }
    }
}
