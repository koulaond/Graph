package core.repository.processing.command;

import core.repository.data.DataBucket;
import core.repository.processing.ActionType;

public class DataPatchCommand extends AbstractDataChangeCommand {

  public DataPatchCommand(Long nodeId, DataBucket data) {
    super(nodeId, data);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.PATCH;
  }
}
