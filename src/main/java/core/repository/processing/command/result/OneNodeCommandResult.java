package core.repository.processing.command.result;

import core.repository.data.ResultStatus;
import core.repository.processing.command.OneNodeCommand;

public abstract class OneNodeCommandResult<C extends OneNodeCommand> implements CommandExecutionResult<C> {
  protected C command;
  protected ResultStatus resultStatus;

  public OneNodeCommandResult(C command, ResultStatus resultStatus) {
    this.resultStatus = resultStatus;
    this.command = command;
  }

  @Override
  public C getCommand() {
    return command;
  }

  @Override
  public ResultStatus getResultStatus() {
    return resultStatus;
  }
}
