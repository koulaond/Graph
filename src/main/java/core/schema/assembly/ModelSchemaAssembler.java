package core.schema.assembly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.ReflectionUtils;

import core.schema.annotations.Node;
import core.schema.assembly.definitions.SchemaDefinition;

public class ModelSchemaAssembler {
  private String name;
  private String basePackage;
  private boolean strict = true;
  private Map<String, Object> additionalInfo;

  public ModelSchemaAssembler() {
    // Default values
    additionalInfo = new HashMap<>();
    strict = true;
  }

  public ModelSchemaAssembler name(String name) {
    this.name = name;
    return this;
  }

  public ModelSchemaAssembler basePackage(String basePackage) {
    this.basePackage = basePackage;
    return this;
  }

  public ModelSchemaAssembler strict(boolean strict) {
    this.strict = strict;
    return this;
  }

  public ModelSchemaAssembler additionalInfo(String key, Object value) {
    this.additionalInfo.put(key, value);
    return this;
  }

  public ModelSchemaAssembler additionalInfo(Map<String, Object> additionalInfo) {
    this.additionalInfo.putAll(additionalInfo);
    return this;
  }

  public String getName() {
    return name;
  }

  public String getBasePackage() {
    return basePackage;
  }

  public boolean isStrict() {
    return strict;
  }

  public Map<String, Object> getAdditionalInfo() {
    return additionalInfo;
  }


  public SchemaDefinition assemble() {
    // TODO replace name check by validator
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalStateException("Schema name bad value.");
    }

    if (basePackage == null || basePackage.trim().isEmpty()) {
      throw new IllegalStateException("Base package bad value");
    }

    SchemaAssembler schemaAssembler = new SchemaAssembler();
    schemaAssembler.additionalInfo(additionalInfo).name(name).strict(strict);
    // TODO collect all @Node classes
    // TODO for each @Node class
    // 1. Collect @Node values
    // 2. Collect all properties
    // 3. Collect all relations

    Set<Class<?>> nodeCLasses = searchNodeClasses(basePackage);
    // 1.
    return null;
  }

  private Set<Class<?>> searchNodeClasses(String basePackage) {
    List<Class<?>> classes = ReflectionUtils.forNames(Stream.of(basePackage).collect(Collectors.toSet()));
    return classes.stream()
        .filter(clazz -> isNode(clazz))
        .collect(Collectors.toSet());
  }

  /**
   * Returns true if the input class or at least one of its superclasses has @{@link Node} annotation present, else false.
   */
  private boolean isNode(Class<?> clazz) {
    while (!clazz.equals(Object.class)) {
      if (clazz.isAnnotationPresent(Node.class)) {
        return true;
      }
      clazz = clazz.getSuperclass();
    }
    return false;
  }
}
