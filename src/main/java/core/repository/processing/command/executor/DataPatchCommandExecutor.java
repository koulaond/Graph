package core.repository.processing.command.executor;

import core.repository.connector.RepositoryConnector;
import core.repository.data.NodeChangeRepositoryResult;
import core.repository.processing.command.DataPatchCommand;
import core.repository.processing.command.result.DataChangeCommandResult;

public class DataPatchCommandExecutor extends AbstractCommandExecutor<DataPatchCommand, DataChangeCommandResult<DataPatchCommand>> {

  public DataPatchCommandExecutor(RepositoryConnector repositoryConnector) {
    super(repositoryConnector);
  }

  @Override
  public DataChangeCommandResult<DataPatchCommand> execute(DataPatchCommand command) {
    NodeChangeRepositoryResult result = repositoryConnector.proceedNodeChange(command.getData());
    DataChangeCommandResult commandResult = new DataChangeCommandResult(command, result.getResultStatus(), result);
    return commandResult;
  }
}
