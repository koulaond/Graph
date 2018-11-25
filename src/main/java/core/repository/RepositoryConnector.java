package core.repository;

import core.repository.data.NodeChangeRepositoryResult;
import core.repository.data.NodeDataBucket;
import core.repository.data.RepositoryResult;

/**
 * Access point to particular repositories.
 */
public interface RepositoryConnector {

  /**
   * Updates single node with new data or creates new if not exists in concrete graphContainer.
   * @param dataBucket node data container
   * @param graphContainer graphContainer
   * @return graphContainer change result
   */
  NodeChangeRepositoryResult patch(NodeDataBucket dataBucket, GraphContainer graphContainer);

  /**
   * Deletes single node with the given id.
   * @param nodeId node identifier
   * @param graphContainer graphContainer
   * @return graphContainer delete result
   */
  RepositoryResult delete(Long nodeId, GraphContainer graphContainer);

  // TODO design query
}
