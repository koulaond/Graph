package core.repository.data;

import java.util.Set;

import core.repository.Repository;

public class RepositoryResult {

  protected ResultStatus resultStatus;
  protected Repository repository;
  protected Set<Error> errors;

  public RepositoryResult(ResultStatus resultStatus, Repository repository, Set<Error> errors) {
    this.resultStatus = resultStatus;
    this.repository = repository;
    this.errors = errors;
  }

  public ResultStatus getResultStatus() {
    return resultStatus;
  }

  public Set<Error> getErrors() {
    return errors;
  }

  public Repository getRepository() {
    return repository;
  }
}
