package core.repository.processing.command;

import core.repository.data.NodeDataBucket;

public class DataPatchCommand extends AbstractDataChangeCommand {

  private DataPatchCommand() {
    // Constructor not allowed
  }
  @Override
  public ActionType getActionType() {
    return ActionType.PATCH;
  }

  public static DataPatchCommandBuilder builder() {
    return new DataPatchCommandBuilder();
  }

  public static class DataPatchCommandBuilder extends AbstractDataChangeCommandBuilder<DataPatchCommand> {

    private DataPatchCommandBuilder() {
      this.command = new DataPatchCommand();
    }

    @Override
    public DataPatchCommandBuilder nodeId(Long nodeId) {
      return (DataPatchCommandBuilder) super.nodeId(nodeId);
    }

    @Override
    public DataPatchCommandBuilder data(NodeDataBucket data) {
      return (DataPatchCommandBuilder) super.data(data);
    }

    @Override
    public DataPatchCommand build() {
      return command;
    }
  }

}
