package core.repository.processing.command.executor;

import core.repository.GraphContainer;
import repository.api.RepositoryConnector;
import core.repository.data.ResultStatus;
import core.repository.processing.command.BatchCommand;
import core.repository.processing.command.executor.provider.CommandExecutorProvider;
import core.repository.processing.command.result.BatchCommandResult;
import core.repository.processing.command.result.CommandExecutionResult;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Creator for node batch command.
 */
public class BatchCommandExecutor extends AbstractCommandExecutor<BatchCommand, BatchCommandResult> {

  private CommandExecutorProvider executorProvider;

  public BatchCommandExecutor(RepositoryConnector repositoryConnector, CommandExecutorProvider executorProvider) {
    super(repositoryConnector);
    this.executorProvider = executorProvider;
  }

  @Override
  public BatchCommandResult execute(BatchCommand command, GraphContainer graphContainer) {
    AtomicBoolean success = new AtomicBoolean(true);
    ResultStatus resultStatus = ResultStatus.FINISHED;
    Set<CommandExecutionResult> subResults = new HashSet<>();

    command.getCommands().forEach(subCommand -> {
      CommandExecutor executor = executorProvider.getExecutorForCommandType(subCommand.getClass());
      CommandExecutionResult result = executor.execute(subCommand, graphContainer);
      subResults.add(result);
      if (ResultStatus.FAILED.equals(result.getResultStatus())) {
        success.set(false);
        return;
      }
    });
    if (false == success.get()) {
      resultStatus = ResultStatus.FAILED;
    }

    BatchCommandResult result = new BatchCommandResult(command, resultStatus, subResults);
    return result;
  }
}
