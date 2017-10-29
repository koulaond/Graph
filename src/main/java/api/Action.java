package api;

@FunctionalInterface
public interface Action<G extends Graph, RESULT> {

    RESULT proceed(G graph);
}
