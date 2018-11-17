package core.repository.data;

import java.util.Set;

import core.repository.Repository;

public class NodeChangeRepositoryResult extends RepositoryResult {
  protected NodeChange nodeChange;

  public NodeChangeRepositoryResult(ResultStatus resultStatus, Repository repository, Set<Error> errors, NodeChange nodeChange) {
    super(resultStatus, repository, errors);
    this.nodeChange = nodeChange;
  }

  public NodeChange getNodeChange() {
    return nodeChange;
  }
}
