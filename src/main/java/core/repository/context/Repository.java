package core.repository.context;

import core.repository.processing.CommandExecutor;
import core.repository.processing.QueryExecutor;

public interface Repository {
  QueryExecutor getQueryExecutor();

  CommandExecutor geCommandExecutor();
}
