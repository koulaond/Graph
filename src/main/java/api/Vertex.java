package api;

import java.util.Set;
import java.util.UUID;

public interface Vertex<E extends Edge> {
    UUID id();

    Set<E> getAllEdges();

    boolean addEdge(E edge);

    default boolean containsEdge(E edge){
        return this.getAllEdges().contains(edge);
    }
}
