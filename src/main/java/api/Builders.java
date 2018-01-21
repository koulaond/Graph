package api;

public final class Builders {

    private Builders() {
        // No Builders instance !!
    }

    public static ConnectionBuilder connectionBuilder() {
        return new ConnectionBuilder();
    }

    public static NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }

    public static GraphBuilder graphBuilder() {
        return new GraphBuilder();
    }

    public static PropertyBuilder propertyBuilder(){
        return new PropertyBuilder();
    }
}
