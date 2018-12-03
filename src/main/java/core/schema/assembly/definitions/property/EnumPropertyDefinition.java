package core.schema.assembly.definitions.property;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

public class EnumPropertyDefinition extends PropertyDefinition {

  private Set<String> enumerations;

  public EnumPropertyDefinition(String propertyName, Set<String> enumerations) {
    this(propertyName,false, false, false, enumerations);
  }

  public EnumPropertyDefinition(String propertyName, boolean mandatory, boolean multiValue, boolean immutable, Set<String> enumerations) {
    super(propertyName, mandatory, multiValue, immutable);
    this.enumerations = unmodifiableSet(requireNonNull(enumerations));
  }

  public Set<String> getEnumerations() {
    return unmodifiableSet(enumerations);
  }
}
