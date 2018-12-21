package core.repository;

import core.repository.processing.command.Command;
import core.repository.processing.command.executor.CommandExecutor;
import core.repository.processing.command.executor.provider.CommandExecutorProvider;
import core.repository.processing.command.executor.provider.DefaultCommandExecutorProvider;
import core.repository.processing.command.result.CommandExecutionResult;
import lombok.extern.slf4j.Slf4j;
import repository.api.RepositoryConnector;

/**
 * Access point to concrete graphContainer. Allows to execute commands and queries on the graphContainer via executors and changes its state.
 */
@Slf4j
public class GraphContainer {

  protected CommandExecutorProvider commandExecutorProvider;

  protected String repositoryName;

  public GraphContainer(RepositoryConnector repositoryConnector, String repositoryName) {
    this.commandExecutorProvider = new DefaultCommandExecutorProvider(repositoryConnector);
    this.repositoryName = repositoryName;
  }

  /**
   * Executes command on the graphContainer.
   *
   * @param command @{@link Command} instance
   *
   * @return command result
   */
  <C extends Command> CommandExecutionResult<C> executeCommand(C command) {
    CommandExecutor executorForCommandType = this.commandExecutorProvider.getExecutorForCommandType(command.getClass());
    if (executorForCommandType == null) {
//      log.error("No executor for command of type ", command.getClass().getName());
      throw new IllegalArgumentException();
    }
    return executorForCommandType.execute(command, this);
  }

  public String getName() {
    return repositoryName;
  }

  private interface GraphContainerExecutionPolicy {
    boolean commandExecutionAllowed();

    boolean queryExecutionAllowed();

    <C extends Command, CR extends CommandExecutionResult<C>> CommandExecutionResult<C> executeCommand(C command,
                                                                                                       CommandExecutor<C, CR> commandExecutor,
                                                                                                       GraphContainer graphContainer);

    // TODO execute query
  }

  private static class AllAllowedExecutionPolicy implements GraphContainerExecutionPolicy {

    @Override
    public boolean commandExecutionAllowed() {
      return true;
    }

    @Override
    public boolean queryExecutionAllowed() {
      return true;
    }

    @Override
    public <C extends Command, CR extends CommandExecutionResult<C>> CommandExecutionResult<C> executeCommand(C command,
                                                                                                              CommandExecutor<C, CR> commandExecutor,
                                                                                                              GraphContainer graphContainer) {
      return commandExecutor.execute(command, graphContainer);
    }
  }
}
