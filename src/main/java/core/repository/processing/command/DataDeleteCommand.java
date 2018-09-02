package core.repository.processing.command;

public class DataDeleteCommand implements OneNodeCommand {
  private Long nodeId;

  private DataDeleteCommand() {
    // Constructor not allowed
  }

  @Override
  public ActionType getActionType() {
    return ActionType.DELETE;
  }

  public static DataDeleteCommandBuilder builder() {
    return new DataDeleteCommandBuilder();
  }

  @Override
  public Long getNodeId() {
    return nodeId;
  }

  public static class DataDeleteCommandBuilder {
    private DataDeleteCommand command;

    private DataDeleteCommandBuilder() {
      this.command = new DataDeleteCommand();
    }

    public DataDeleteCommandBuilder nodeId(Long nodeId) {
      this.command.nodeId = nodeId;
      return this;
    }

    public DataDeleteCommand build() {
      return command;
    }
  }
}
