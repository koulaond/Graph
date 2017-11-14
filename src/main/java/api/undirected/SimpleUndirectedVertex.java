package api.undirected;

import api.AbstractGraphElement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class SimpleUndirectedVertex<E extends UndirectedEdge>
        extends AbstractGraphElement
        implements UndirectedVertex<E> {

    protected final Set<E> edges;
    protected final UUID id;

    public SimpleUndirectedVertex() {
        this.id = UUID.randomUUID();
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
}
