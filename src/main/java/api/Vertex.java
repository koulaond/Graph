package api;

import java.util.Set;

public interface Vertex<E extends Edge> {
    Long id();

    Set<E> getAllEdges();
}
