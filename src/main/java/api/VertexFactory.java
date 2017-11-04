package api;

@FunctionalInterface
public interface VertexFactory<V extends Vertex> {
    V create();
}
