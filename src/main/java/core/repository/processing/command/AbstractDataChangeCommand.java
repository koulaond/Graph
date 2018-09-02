package core.repository.processing.command;

import java.io.Serializable;

import core.repository.data.NodeDataBucket;
import core.repository.data.DataUnit;

public abstract class AbstractDataChangeCommand<DT extends DataUnit<? extends Serializable>> implements DataChangeCommand {

  protected Long nodeId;
  protected NodeDataBucket data;

  public AbstractDataChangeCommand(Long nodeId, NodeDataBucket data) {
    this.nodeId = nodeId;
    this.data = data;
  }

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
}
