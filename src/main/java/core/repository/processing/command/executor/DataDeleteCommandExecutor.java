package core.repository.processing.command.executor;

import core.repository.GraphContainer;
import core.repository.RepositoryConnector;
import core.repository.data.RepositoryResult;
import core.repository.processing.command.DataDeleteCommand;
import core.repository.processing.command.result.DataDeleteCommandResult;

/**
 * Creator for node deletion command.
 */
public class DataDeleteCommandExecutor extends AbstractCommandExecutor<DataDeleteCommand, DataDeleteCommandResult> {

  public DataDeleteCommandExecutor(RepositoryConnector repositoryConnector) {
    super(repositoryConnector);
  }

  @Override
  public DataDeleteCommandResult execute(DataDeleteCommand command, GraphContainer graphContainer) {
    RepositoryResult result = repositoryConnector.delete(command.getNodeId(), graphContainer);
    DataDeleteCommandResult commandResult = new DataDeleteCommandResult(command, result.getResultStatus(), result);
    return commandResult;
  }
}
