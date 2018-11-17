package core.repository.processing.command.executor;

import core.repository.Repository;
import core.repository.RepositoryConnector;
import core.repository.data.NodeChangeRepositoryResult;
import core.repository.processing.command.DataPatchCommand;
import core.repository.processing.command.result.DataChangeCommandResult;

public class DataPatchCommandExecutor extends AbstractCommandExecutor<DataPatchCommand, DataChangeCommandResult<DataPatchCommand>> {

  public DataPatchCommandExecutor(RepositoryConnector repositoryConnector) {
    super(repositoryConnector);
  }

  @Override
  public DataChangeCommandResult<DataPatchCommand> execute(DataPatchCommand command, Repository repository) {
    NodeChangeRepositoryResult result = repositoryConnector.patch(command.getData(), repository);
    DataChangeCommandResult commandResult = new DataChangeCommandResult(command, result.getResultStatus(), result);
    return commandResult;
  }
}
