package api;


public interface Edge<V extends Vertex> {
    V left();

    V right();
}
