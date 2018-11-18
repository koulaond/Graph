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

  /**
   * All active repositories. Map contains pairs schemaName:repository.
   */
  private Map<String, Repository> activeRepositories;

  private RepositoryConnector repositoryConnector;

  private RepositoryContext(RepositoryConnector repositoryConnector) {
    // Do not use constructor for singleton context!!
    this.repositoryConnector = repositoryConnector;
    this.activeRepositories = new HashMap<>();
    cachePersistedRepositories();
  }

  /**
   * Loads all persisted repositories from the connector.
   */
  private void cachePersistedRepositories() {

  }

  /**
   * Generates new schema from all annotated classes in the package.
   *
   * @param repositoryName repository name
   * @param pckg package the new schema is built from
   *
   * @return @{@link SchematicRepository} instance
   */
  SchematicRepository createRepositoryFromModel(String repositoryName, String pckg) {
    Schema newSchema = SchemaBuilderFactory.fromModel()
        .packageName(pckg)
        .schemaName(repositoryName)
        .build();
    return new SchematicRepository(this.repositoryConnector, newSchema);
  }

  /**
   * Creates simple repository.
   * @param repositoryName repository name
   * @return @{@link Repository} instance
   */
  Repository createRepository(String repositoryName) {
    return new Repository(repositoryConnector, repositoryName);
  }

  /**
   * Creates new repository definition in DB.
   * @param repositoryToPersist repository to be persisted
   * @return true if repository was persisted or not
   */
  boolean persistRepository(Repository repositoryToPersist) {
    Repository foundByName = activeRepositories.values()
        .stream()
        .filter(repository -> repository.getName().equals(repositoryToPersist.getName()))
        .findFirst()
        .orElse(null);

    if (foundByName != null) {
      log.warn("Repository with name {} found and is returned. Returning this existing repository and no other action is performed.", foundByName.getName());
      return false;
    }
    activeRepositories.put(repositoryToPersist.getName(), repositoryToPersist);
    return true;
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
      log.error("Repository context has not been initialized yet.");
      throw new IllegalStateException();
    }
    return context;
  }

  /**
   * Initializes {@link RepositoryContext} singleton.
   *
   * @param connector repository connector implementation
   *
   * @throws IllegalStateException if contet is already initialized
   */
  public static void init(RepositoryConnector connector) {
    if (context != null) {
      log.error("Context is already initialized.");
      throw new IllegalStateException();
    }
    context = new RepositoryContext(connector);
  }

}
