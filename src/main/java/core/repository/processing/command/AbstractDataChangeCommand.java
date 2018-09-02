package core.repository.processing.command;

import java.io.Serializable;

import core.repository.data.NodeDataBucket;
import core.repository.data.DataUnit;

public abstract class AbstractDataChangeCommand<DT extends DataUnit<? extends Serializable>> implements DataChangeCommand {

  protected Long nodeId;
  protected NodeDataBucket data;

  @Override
  public NodeDataBucket getData() {
    return null;
  }

  @Override
  public Long getNodeId() {
    return null;
  }

  @Override
  public abstract ActionType getActionType();

  public static abstract class AbstractDataChangeCommandBuilder<C extends AbstractDataChangeCommand> {
    protected C command;

    protected AbstractDataChangeCommandBuilder() {

    }

    public AbstractDataChangeCommandBuilder nodeId(Long nodeId) {
      this.command.nodeId = nodeId;
      return this;
    }

    public AbstractDataChangeCommandBuilder data(NodeDataBucket data) {
      this.command.data = data;
      return this;
    }

    public abstract C build();
  }
}
