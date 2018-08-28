package core.repository.processing.command;

import java.io.Serializable;

import core.repository.data.DataBucket;
import core.repository.data.DataUnit;

public interface DataChangeCommand<DT extends DataUnit<? extends Serializable>>  extends OneNodeCommand {
  DataBucket<DT> getData();
}
