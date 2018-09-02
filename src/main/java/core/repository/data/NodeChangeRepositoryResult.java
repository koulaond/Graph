package core.repository.data;

import java.util.Set;

public class NodeChangeRepositoryResult extends RepositoryResult {
  protected NodeChange nodeChange;

  public NodeChangeRepositoryResult(ResultStatus resultStatus, Set<Error> errors, NodeChange nodeChange) {
    super(resultStatus, errors);
    this.nodeChange = nodeChange;
  }

  public NodeChange getNodeChange() {
    return nodeChange;
  }
}
