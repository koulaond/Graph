package core.repository.data;

import java.util.Set;

public class RepositoryResult {

  protected ResultStatus resultStatus;
  protected Set<Error> errors;

  public RepositoryResult(ResultStatus resultStatus, Set<Error> errors) {
    this.resultStatus = resultStatus;
    this.errors = errors;
  }

  public ResultStatus getResultStatus() {
    return resultStatus;
  }

  public Set<Error> getErrors() {
    return errors;
  }
}
