package core.context;

import core.schema.definitions.GraphDefinition;

public interface RepositoryContext {
    GraphDefinition getGraphDefinition(String schema);
}
