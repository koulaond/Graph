package core.repository.processing.command.result;

import core.repository.processing.command.OneNodeCommand;

public abstract class OneNodeCommandResult<C extends OneNodeCommand> implements CommandExecutionResult<C> {
  protected C command;
  protected ResultStatus resultStatus;

  @Override
  public C getCommand() {
    return command;
  }

  public void setCommand(C command) {
    this.command = command;
  }

  @Override
  public ResultStatus getResultStatus() {
    return resultStatus;
  }

  public void setResultStatus(ResultStatus resultStatus) {
    this.resultStatus = resultStatus;
  }
}
