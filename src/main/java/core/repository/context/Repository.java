package core.repository.context;

import core.repository.processing.command.executors.CommandExecutor;
import core.repository.processing.QueryExecutor;

public interface Repository {
  QueryExecutor getQueryExecutor();

  CommandExecutor geCommandExecutor();
}
