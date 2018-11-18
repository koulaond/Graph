package core.repository.processing.command.executor;

import core.repository.Repository;
import core.repository.processing.command.Command;
import core.repository.processing.command.result.CommandExecutionResult;

/**
 * Executor provides some action on the repository according to the given command.
 * @param <C> @{@link Command} type
 * @param <CR> @{@link CommandExecutionResult} type
 */
public interface CommandExecutor<C extends Command, CR extends CommandExecutionResult<C>> {

  /**
   * Executes the command on the repository and returns the result.
   * @param command @{@link Command} instance
   * @param repository @{@link Repository} instance
   * @return @{@link CommandExecutionResult} instance
   */
  CR execute(C command, Repository repository);
}
