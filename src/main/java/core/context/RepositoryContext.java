package core.context;

import model.definitions.GraphDefinition;

public interface RepositoryContext {
    GraphDefinition getGraphDefinition(String schema);
}
