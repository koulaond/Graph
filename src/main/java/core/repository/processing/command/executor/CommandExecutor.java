package core.repository.processing.command.executor;

import core.repository.processing.command.Command;
import core.repository.processing.command.result.CommandExecutionResult;

public interface CommandExecutor<C extends Command, CR extends CommandExecutionResult<C>> {

  CR execute(C command);
}
