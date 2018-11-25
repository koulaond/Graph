package core.repository;

import java.util.HashMap;
import java.util.Map;

import core.schema.Schema;
import core.schema.SchemaBuilderFactory;
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
     * Generates new schema from all annotated classes in the package.
     *
     * @param containerName graphContainer name
     * @param pckg package the new schema is built from
     *
     * @return @{@link SchematicGraphContainer} instance
     */
    SchematicGraphContainer createContainerFromModel(String containerName, String pckg) {
      Schema newSchema = SchemaBuilderFactory.fromModel()
          .packageName(pckg)
          .schemaName(containerName)
          .build();
      SchematicGraphContainer graphContainer = new SchematicGraphContainer(this.repositoryConnector, newSchema);
      persistContainer(graphContainer);
      return graphContainer;
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
