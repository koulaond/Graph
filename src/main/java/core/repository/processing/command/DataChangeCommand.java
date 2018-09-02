package core.repository.processing.command;

import core.repository.data.NodeDataBucket;

public interface DataChangeCommand extends OneNodeCommand {
  NodeDataBucket getData();
}
