package core.repository.data;

import java.util.Set;

import core.repository.GraphContainer;

public class RepositoryResult {

  protected ResultStatus resultStatus;
  protected GraphContainer graphContainer;
  protected Set<Error> errors;

  public RepositoryResult(ResultStatus resultStatus, GraphContainer graphContainer, Set<Error> errors) {
    this.resultStatus = resultStatus;
    this.graphContainer = graphContainer;
    this.errors = errors;
  }

  public ResultStatus getResultStatus() {
    return resultStatus;
  }

  public Set<Error> getErrors() {
    return errors;
  }

  public GraphContainer getGraphContainer() {
    return graphContainer;
  }
}
