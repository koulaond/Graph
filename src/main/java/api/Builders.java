package api;

public class Builders {

    private Builders() {
        // No Builders instance !!
    }

    public static ConnectionBuilder edgeBuilder() {
        return new ConnectionBuilder();
    }

    public static NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }

    public static GraphBuilder graphBuilder() {
        return new GraphBuilder();
    }
}
