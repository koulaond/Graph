package api;

public class Builders {

    private Builders() {
        // No Builders instance !!
    }

    public static EdgeBuilder edgeBuilder() {
        return new EdgeBuilder();
    }

    public static NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }

    public static GraphBuilder graphBuilder() {
        return new GraphBuilder();
    }
}
