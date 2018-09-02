package core.repository.processing.command;

import core.repository.data.NodeDataBucket;

public class DataPatchCommand extends AbstractDataChangeCommand {

  public DataPatchCommand(Long nodeId, NodeDataBucket data) {
    super(nodeId, data);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.PATCH;
  }
}
