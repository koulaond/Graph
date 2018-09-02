package core.repository.processing.command.result;

import core.repository.data.RepositoryResult;
import core.repository.data.ResultStatus;
import core.repository.processing.command.DataDeleteCommand;

public class DataDeleteCommandResult extends OneNodeCommandResult<DataDeleteCommand> {

  private RepositoryResult result;

  public DataDeleteCommandResult(DataDeleteCommand command, ResultStatus resultStatus, RepositoryResult result) {
    super(command, resultStatus);
    this.result = result;
  }

  public RepositoryResult getResult() {
    return result;
  }
}
