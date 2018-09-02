package core.repository.processing.command.result;

import core.repository.data.NodeChangeRepositoryResult;
import core.repository.data.ResultStatus;
import core.repository.processing.command.DataChangeCommand;

public class DataChangeCommandResult<C extends DataChangeCommand> extends OneNodeCommandResult<C> implements CommandExecutionResult<C> {

  private NodeChangeRepositoryResult result;

  public DataChangeCommandResult(C command, ResultStatus resultStatus,  NodeChangeRepositoryResult result) {
    super(command, resultStatus);
    this.result = result;
  }

  public NodeChangeRepositoryResult getResult() {
    return result;
  }
}
