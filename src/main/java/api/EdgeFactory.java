package api;

@FunctionalInterface
public interface EdgeFactory<E> {
    E create();
}
