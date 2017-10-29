package api;

public interface SearchAction<G extends Graph, RESULT> extends Action<G, RESULT> {

    void onEntered(Action action);

    void onFinished(Action action);
}
