package core.schema.assemble.definitions.property;

import java.util.Set;

public class EnumPropertyDefinition extends PropertyDefinition {

  private Set<String> enumerations;

  public EnumPropertyDefinition(boolean mandatory, boolean multiValue, boolean immutable, Set<String> enumerations) {
    super(mandatory, multiValue, immutable);
    this.enumerations = enumerations;
  }

  public Set<String> getEnumerations() {
    return enumerations;
  }
}
