package core.repository.processing.command.result;

import java.util.Collection;

import core.repository.processing.command.DataChangeCommand;

public class DataChangeCommandResult<C extends DataChangeCommand> extends OneNodeCommandResult<C> implements CommandExecutionResult<C> {

  private Collection<ResultDataChange> dataChanges;

  public DataChangeCommandResult(Collection<ResultDataChange> dataChanges) {
    this.dataChanges = dataChanges;
  }

  public Collection<ResultDataChange> getDataChanges() {
    return dataChanges;
  }
}
