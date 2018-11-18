package core.repository;

import core.repository.processing.command.Command;
import core.repository.processing.command.executor.CommandExecutor;
import core.repository.processing.command.executor.provider.CommandExecutorProvider;
import core.repository.processing.command.executor.provider.DefaultCommandExecutorProvider;
import core.repository.processing.command.result.CommandExecutionResult;
import lombok.extern.slf4j.Slf4j;

/**
 * Access point to concrete repository. Allows to execute commands and queries on the repository via executors and changes its state.
 */
@Slf4j
public class Repository {

  protected CommandExecutorProvider commandExecutorProvider;

  protected String repositoryName;

  public Repository(RepositoryConnector repositoryConnector, String repositoryName) {
    this.commandExecutorProvider = new DefaultCommandExecutorProvider(repositoryConnector);
    this.repositoryName = repositoryName;
  }

  /**
   * Executes command on the repository.
   * @param command @{@link Command} instance
   * @return command result
   */
  <C extends Command> CommandExecutionResult<C> executeCommand(C command) {
    CommandExecutor executorForCommandType = this.commandExecutorProvider.getExecutorForCommandType(command.getClass());
    if (executorForCommandType == null) {
      log.error("No executor for command of type ", command.getClass().getName());
      throw new IllegalArgumentException();
    }
    return executorForCommandType.execute(command, this);
  }

  public String getName() {
    return repositoryName;
  }
}
