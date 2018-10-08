package core.repository.connector;

import core.repository.data.NodeChangeRepositoryResult;
import core.repository.data.NodeDataBucket;
import core.repository.data.RepositoryResult;

public interface RepositoryConnector {

  NodeChangeRepositoryResult patch(NodeDataBucket dataBucket);

  RepositoryResult delete(Long nodeId);

  // design query

}
