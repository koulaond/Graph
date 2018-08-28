package core.repository.processing.command;

import core.repository.processing.ActionType;

public class DataDeleteCommand implements OneNodeCommand {
  private Long nodeId;

  public DataDeleteCommand(Long nodeId) {
    this.nodeId = nodeId;
  }

  @Override
  public ActionType getActionType() {
    return ActionType.DELETE;
  }

  @Override
  public Long getNodeId() {
    return nodeId;
  }
}
