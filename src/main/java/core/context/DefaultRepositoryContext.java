package core.context;

public class DefaultRepositoryContext implements RepositoryContext {

    // TODO
    private static DefaultRepositoryContext context;

    private DefaultRepositoryContext() {
        // No instances using constructor!!
    }

    @Override
    public GraphDefinition getGraphDefinition(String schema) {
        // TODO
        return null;
    }

    public static DefaultRepositoryContext getInstance() {
        return context;
    }
}
