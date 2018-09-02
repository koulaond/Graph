package core.repository.processing.command.executor;

import core.repository.connector.RepositoryConnector;
import core.repository.data.RepositoryResult;
import core.repository.processing.command.DataDeleteCommand;
import core.repository.processing.command.result.DataDeleteCommandResult;

public class DataDeleteCommandExecutor extends AbstractCommandExecutor<DataDeleteCommand, DataDeleteCommandResult> {

  public DataDeleteCommandExecutor(RepositoryConnector repositoryConnector) {
    super(repositoryConnector);
  }

  @Override
  public DataDeleteCommandResult execute(DataDeleteCommand command) {
    RepositoryResult result = repositoryConnector.deleteNode(command.getNodeId());
    DataDeleteCommandResult commandResult = new DataDeleteCommandResult(command, result.getResultStatus(), result);
    return commandResult;
  }
}
