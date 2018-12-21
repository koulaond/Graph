package core.repository.processing.command.executor;

import core.repository.GraphContainer;
import repository.api.RepositoryConnector;
import core.repository.data.NodeChangeRepositoryResult;
import core.repository.processing.command.DataCreateCommand;
import core.repository.processing.command.result.DataChangeCommandResult;

/**
 * Creator for node creation command.
 */
public class DataCreateCommandExecutor extends AbstractCommandExecutor<DataCreateCommand, DataChangeCommandResult<DataCreateCommand>> {

  public DataCreateCommandExecutor(RepositoryConnector repositoryConnector) {
    super(repositoryConnector);
  }

  @Override
  public DataChangeCommandResult<DataCreateCommand> execute(DataCreateCommand command, GraphContainer graphContainer) {
    NodeChangeRepositoryResult result = repositoryConnector.patch(command.getData(), graphContainer);
    DataChangeCommandResult commandResult = new DataChangeCommandResult(command, result.getResultStatus(), result);
    return commandResult;
  }
}
