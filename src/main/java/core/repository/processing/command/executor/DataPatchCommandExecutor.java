package core.repository.processing.command.executor;

import core.repository.GraphContainer;
import core.repository.RepositoryConnector;
import core.repository.data.NodeChangeRepositoryResult;
import core.repository.processing.command.DataPatchCommand;
import core.repository.processing.command.result.DataChangeCommandResult;

/**
 * Creator for node update command.
 */
public class DataPatchCommandExecutor extends AbstractCommandExecutor<DataPatchCommand, DataChangeCommandResult<DataPatchCommand>> {

  public DataPatchCommandExecutor(RepositoryConnector repositoryConnector) {
    super(repositoryConnector);
  }

  @Override
  public DataChangeCommandResult<DataPatchCommand> execute(DataPatchCommand command, GraphContainer graphContainer) {
    NodeChangeRepositoryResult result = repositoryConnector.patch(command.getData(), graphContainer);
    DataChangeCommandResult commandResult = new DataChangeCommandResult(command, result.getResultStatus(), result);
    return commandResult;
  }
}
