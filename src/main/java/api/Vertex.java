package api;

import java.util.Set;
import java.util.UUID;

public interface Vertex<E extends Edge> extends GraphElement {

    Set<E> getAllEdges();
}
