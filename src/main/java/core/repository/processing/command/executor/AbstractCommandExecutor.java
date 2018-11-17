package core.repository.processing.command.executor;

import core.repository.RepositoryConnector;
import core.repository.processing.command.Command;
import core.repository.processing.command.result.CommandExecutionResult;

public  abstract class AbstractCommandExecutor<C extends Command, CR extends CommandExecutionResult<C>> implements CommandExecutor<C, CR> {

  protected RepositoryConnector repositoryConnector;

  public AbstractCommandExecutor(RepositoryConnector repositoryConnector) {
    this.repositoryConnector = repositoryConnector;
  }
}
