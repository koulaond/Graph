package core.repository.processing.command.executor.provider;

import core.repository.processing.command.*;
import core.repository.processing.command.executor.*;
import repository.api.RepositoryConnector;

import java.util.HashMap;
import java.util.Map;

public class DefaultCommandExecutorProvider implements CommandExecutorProvider {

  private Map<Class<? extends Command>, CommandExecutor> executors;
  private boolean initialized = false;

  public DefaultCommandExecutorProvider(RepositoryConnector connector) {
    this.executors = new HashMap<>();
  }

  public void init(RepositoryConnector connector) {
    this.executors = new HashMap<>();
    this.executors.put(DataCreateCommand.class, new DataCreateCommandExecutor(connector));
    this.executors.put(DataPatchCommand.class, new DataPatchCommandExecutor(connector));
    this.executors.put(DataDeleteCommand.class, new DataDeleteCommandExecutor(connector));
    this.executors.put(BatchCommand.class, new BatchCommandExecutor(connector, this));
    this.initialized = true;
  }

  @Override
  public CommandExecutor getExecutorForCommandType(Class<? extends Command> clazz) {
    if (!initialized) {
      throw new IllegalStateException("Provider has not been initialized yet.");
    }
    CommandExecutor executor = executors.get(clazz);
    if (executor == null) {
      throw new IllegalStateException(String.format("No executor defined for class %s", clazz.getName()));
    }
    return executor;
  }
}
