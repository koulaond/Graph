package core.context;

import core.schema.metamodel.GraphDefinition;

public interface RepositoryContext {
    GraphDefinition getGraphDefinition(String schema);
}
