package core.repository.processing.command.result;

import java.util.Set;

import core.repository.data.ResultStatus;
import core.repository.processing.command.BatchCommand;

public class BatchCommandResult implements CommandExecutionResult<BatchCommand> {

  private BatchCommand command;
  private ResultStatus resultStatus;
  private Set<CommandExecutionResult> results;

  public BatchCommandResult(BatchCommand command, ResultStatus resultStatus, Set<CommandExecutionResult> results) {
    this.command = command;
    this.resultStatus = resultStatus;
    this.results = results;
  }

  @Override
  public BatchCommand getCommand() {
    return command;
  }

  public ResultStatus getResultStatus() {
    return resultStatus;
  }

  public Set<CommandExecutionResult> getResults() {
    return results;
  }
}
