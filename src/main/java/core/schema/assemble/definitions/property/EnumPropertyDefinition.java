package core.schema.assemble.definitions.property;

import java.util.Set;

public class EnumPropertyDefinition extends PropertyDefinition {

  private Set<String> enumerations;

  public EnumPropertyDefinition(String propertyName, Set<String> enumerations) {
    this(propertyName,false, false, false, enumerations);
  }

  public EnumPropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable, Set<String> enumerations) {
    super(propertyName, mandatory, multiValue, immutable);
    this.enumerations = enumerations;
  }

  public Set<String> getEnumerations() {
    return enumerations;
  }
}
