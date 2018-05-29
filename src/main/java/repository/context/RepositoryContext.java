package repository.context;

import repository.schema.metamodel.GraphDefinition;

public interface RepositoryContext {
    GraphDefinition getGraphDefinition(String schema);
}
