package api;

@FunctionalInterface
public interface EdgeFactory<E extends Edge> {
    E create();
}
