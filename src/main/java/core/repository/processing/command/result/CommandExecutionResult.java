package core.repository.processing.command.result;

import core.repository.processing.command.OneNodeCommand;

public interface CommandExecutionResult<C extends OneNodeCommand> {
  C getCommand();

  ResultStatus getResultStatus();
}
