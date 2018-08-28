package core.repository.processing.command.executors;

import core.repository.processing.command.OneNodeCommand;
import core.repository.processing.command.result.CommandExecutionResult;

public interface CommandExecutor<C extends OneNodeCommand, CR extends CommandExecutionResult<C>> {

  CR execute(C command);
}
