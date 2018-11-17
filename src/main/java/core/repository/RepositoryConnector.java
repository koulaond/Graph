package core.repository;

import core.repository.data.NodeChangeRepositoryResult;
import core.repository.data.NodeDataBucket;
import core.repository.data.RepositoryResult;

public interface RepositoryConnector {

  NodeChangeRepositoryResult patch(NodeDataBucket dataBucket, Repository repository);

  RepositoryResult delete(Long nodeId, Repository repository);

  Repository getRepository(String repositoryName);

  Repository persistRepository(Repository repository);

  // design query

}
