package core.repository.context;

import java.util.HashMap;
import java.util.Map;

import core.repository.connector.RepositoryConnector;
import core.repository.processing.command.Command;
import core.repository.processing.command.executor.CommandExecutor;
import core.repository.processing.command.result.CommandExecutionResult;

public abstract class Repository {

  protected RepositoryConnector repositoryConnector;

  protected Map<Class<? extends Command>, CommandExecutor> commandExecutors;

  public Repository(RepositoryConnector repositoryConnector) {
    this.repositoryConnector = repositoryConnector;
    initExecutors();
  }

  private void initExecutors() {
    this.commandExecutors = new HashMap<>();
    // TODO
  }

  abstract <C extends Command> CommandExecutionResult<C> executeCommand(C command);

  // TODO design query
  //executeQuery(Query query);
}
