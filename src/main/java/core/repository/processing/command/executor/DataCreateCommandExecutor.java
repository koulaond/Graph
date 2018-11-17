package core.repository.processing.command.executor;

import core.repository.Repository;
import core.repository.RepositoryConnector;
import core.repository.data.NodeChangeRepositoryResult;
import core.repository.processing.command.DataCreateCommand;
import core.repository.processing.command.result.DataChangeCommandResult;

public class DataCreateCommandExecutor extends AbstractCommandExecutor<DataCreateCommand, DataChangeCommandResult<DataCreateCommand>> {

  public DataCreateCommandExecutor(RepositoryConnector repositoryConnector) {
    super(repositoryConnector);
  }

  @Override
  public DataChangeCommandResult<DataCreateCommand> execute(DataCreateCommand command, Repository repository) {
    NodeChangeRepositoryResult result = repositoryConnector.patch(command.getData(), repository);
    DataChangeCommandResult commandResult = new DataChangeCommandResult(command, result.getResultStatus(), result);
    return commandResult;
  }
}
