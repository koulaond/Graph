package api;

import java.util.Set;

public interface Vertex {
    Long id();

    Set<Edge> edges();
}
