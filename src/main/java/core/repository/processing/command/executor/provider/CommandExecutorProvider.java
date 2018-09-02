package core.repository.processing.command.executor.provider;

import core.repository.processing.command.Command;
import core.repository.processing.command.executor.CommandExecutor;

public interface CommandExecutorProvider {

  CommandExecutor getExecutorForCommandType(Class<? extends Command> clazz);
}
