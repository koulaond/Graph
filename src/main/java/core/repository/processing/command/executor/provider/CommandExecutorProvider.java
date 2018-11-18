package core.repository.processing.command.executor.provider;

import core.repository.processing.command.Command;
import core.repository.processing.command.executor.CommandExecutor;

/**
 * Executors provider. It provides executor for every supported command.
 */
public interface CommandExecutorProvider {

  /**
   *
   * @param clazz command class
   * @return @{@link CommandExecutor} instance
   */
  CommandExecutor getExecutorForCommandType(Class<? extends Command> clazz);
}
