package core.repository.data;

import java.util.Set;

import core.repository.GraphContainer;

public class NodeChangeRepositoryResult extends RepositoryResult {
  protected NodeChange nodeChange;

  public NodeChangeRepositoryResult(ResultStatus resultStatus, GraphContainer graphContainer, Set<Error> errors, NodeChange nodeChange) {
    super(resultStatus, graphContainer, errors);
    this.nodeChange = nodeChange;
  }

  public NodeChange getNodeChange() {
    return nodeChange;
  }
}
