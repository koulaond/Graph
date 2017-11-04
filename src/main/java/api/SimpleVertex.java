package api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class SimpleVertex<E extends Edge> implements Vertex<E> {
    private final Set<E> edges;
    private final UUID id;

    public SimpleVertex() {
        this.id = UUID.randomUUID();
        this.edges = new HashSet<>();
    }

    public UUID id() {
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
        if (!(obj instanceof SimpleVertex)) return false;
        return this.id().equals(((SimpleVertex) obj).id());
    }
}
