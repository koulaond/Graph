package core.repository.processing.command;

import java.io.Serializable;

import core.repository.data.DataBucket;
import core.repository.data.DataUnit;
import core.repository.processing.ActionType;

public abstract class AbstractDataChangeCommand<DT extends DataUnit<? extends Serializable>> implements DataChangeCommand<DT> {

  protected Long nodeId;
  protected DataBucket<DT> data;

  public AbstractDataChangeCommand(Long nodeId, DataBucket<DT> data) {
    this.nodeId = nodeId;
    this.data = data;
  }

  @Override
  public DataBucket<DT> getData() {
    return null;
  }

  @Override
  public Long getNodeId() {
    return null;
  }

  @Override
  public abstract ActionType getActionType();
}
