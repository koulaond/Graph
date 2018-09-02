package core.repository.processing.command;

import core.repository.data.DataBucket;

public class DataCreateCommand extends AbstractDataChangeCommand {

  public DataCreateCommand(Long nodeId, DataBucket data) {
    super(nodeId, data);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.CREATE;
  }
}
