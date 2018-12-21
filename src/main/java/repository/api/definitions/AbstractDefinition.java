package repository.api.definitions;

import static java.util.Objects.requireNonNull;

public class AbstractDefinition {

  /**
   * Unique type for nodes that are defined by this meta-node. It is something like class type for nodes.
   */
  protected String name;

  public AbstractDefinition(String name) {
    this.name = requireNonNull(name);
  }

  public String getName() {
    return name;
  }
}
