package core.repository;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepositoryContext {
  /**
   * Context singleton instance.
   */
  private static RepositoryContext context;

  private RepositoryConnector repositoryConnector;

  private RepositoryContext(RepositoryConnector repositoryConnector) {
    // Do not use constructor for singleton context!!
    this.repositoryConnector = repositoryConnector;
  }

  /**
   * Returns @{@link RepositoryContext} singleton.
   *
   * @return context
   *
   * @throws IllegalStateException if context is not initialized yet.
   */
  public static RepositoryContext instance() {
    if (context == null) {
      log.error("GraphContainer context has not been initialized yet.");
      throw new IllegalStateException();
    }
    return context;
  }

  /**
   * Initializes {@link RepositoryContext} singleton.
   *
   * @param connector graphContainer connector implementation
   *
   * @throws IllegalStateException if context is already initialized
   */
  public static void init(RepositoryConnector connector) {
    if (context != null) {
      log.error("Context is already initialized.");
      throw new IllegalStateException();
    }
    context = new RepositoryContext(connector);
  }

  private static class ContainerManager {

    private RepositoryConnector repositoryConnector;

    /**
     * All active repositories. Map contains pairs name:graphContainer.
     */
    private Map<String, GraphContainer> graphContainers;

    public ContainerManager(RepositoryConnector repositoryConnector) {
      this.repositoryConnector = repositoryConnector;
      this.graphContainers = new HashMap<>();
    }

    /**
     * Creates simple graphContainer.
     *
     * @param containerName graphContainer name
     *
     * @return @{@link GraphContainer} instance
     */
    GraphContainer createContainer(String containerName) {
      return new GraphContainer(repositoryConnector, containerName);
    }

    /**
     * Creates new graphContainer definition in DB.
     *
     * @param graphContainerToPersist graphContainer to be persisted
     *
     * @return true if graphContainer was persisted or not
     */
    private boolean persistContainer(GraphContainer graphContainerToPersist) {

      return true;
    }
  }

}
