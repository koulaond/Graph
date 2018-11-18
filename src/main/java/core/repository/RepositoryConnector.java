package core.repository;

import core.repository.data.NodeChangeRepositoryResult;
import core.repository.data.NodeDataBucket;
import core.repository.data.RepositoryResult;

/**
 * Access point to particular repositories.
 */
public interface RepositoryConnector {

  /**
   * Updates single node with new data or creates new if not exists in concrete repository.
   * @param dataBucket node data container
   * @param repository repository
   * @return repository change result
   */
  NodeChangeRepositoryResult patch(NodeDataBucket dataBucket, Repository repository);

  /**
   * Deletes single node with the given id.
   * @param nodeId node identifier
   * @param repository repository
   * @return repository delete result
   */
  RepositoryResult delete(Long nodeId, Repository repository);

  /**
   * Returns repository info by repository name.
   * @param repositoryName repository name
   */
  Repository getRepository(String repositoryName);

  /**
   * Creates new repository if does not exist.
   * @param repository @{@link Repository} to be created
   * @return created repository
   */
  Repository createNewRepository(Repository repository);

  // TODO design query
}
