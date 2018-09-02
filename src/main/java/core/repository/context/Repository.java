package core.repository.context;

import core.repository.processing.command.executor.CommandExecutor;
import core.repository.processing.QueryExecutor;

public interface Repository {
  QueryExecutor getQueryExecutor();

  CommandExecutor geCommandExecutor();
}
