package repository;

import repository.schema.metamodel.DefaultGraphDefinition;

public interface RepositoryContext {
    DefaultGraphDefinition getGraphInfo();
}
