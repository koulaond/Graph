package core.repository.processing.command;

import core.repository.data.NodeDataBucket;

public class DataCreateCommand extends AbstractDataChangeCommand {

  public DataCreateCommand(Long nodeId, NodeDataBucket data) {
    super(nodeId, data);
  }

  @Override
  public ActionType getActionType() {
    return ActionType.CREATE;
  }
}
