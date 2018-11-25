package core.repository.processing.query;

import core.repository.GraphContainer;
import core.repository.RepositoryConnector;

public class OneNodeQueryExecutor {
  private RepositoryConnector repositoryConnector;

  public OneNodeQueryExecutor(RepositoryConnector repositoryConnector) {
    this.repositoryConnector = repositoryConnector;
  }

  OneNodeQueryResult executeQuery(OneNodeQuery query, GraphContainer graphContainer) {
    // TODO implement query execution
    return null;
  }
}
