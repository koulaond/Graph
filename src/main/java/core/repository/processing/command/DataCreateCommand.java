package core.repository.processing.command;

import core.repository.data.NodeDataBucket;

public class DataCreateCommand extends AbstractDataChangeCommand {

  private DataCreateCommand() {
    // Constructor not allowed
  }

  @Override
  public ActionType getActionType() {
    return ActionType.CREATE;
  }

  public static DataCreateCommandBuilder builder() {
    return new DataCreateCommandBuilder();
  }

  public static class DataCreateCommandBuilder extends AbstractDataChangeCommandBuilder<DataCreateCommand> {

    private DataCreateCommandBuilder() {
      this.command = new DataCreateCommand();
    }

    @Override
    public DataCreateCommandBuilder nodeId(Long nodeId) {
      return (DataCreateCommandBuilder) super.nodeId(nodeId);
    }

    @Override
    public DataCreateCommandBuilder data(NodeDataBucket data) {
      return (DataCreateCommandBuilder) super.data(data);
    }

    @Override
    public DataCreateCommand build() {
      return command;
    }
  }


}
