package core.repository.processing.command.result;

import core.repository.data.ResultStatus;
import core.repository.processing.command.Command;

public interface CommandExecutionResult<C extends Command> {
  C getCommand();

  ResultStatus getResultStatus();
}
