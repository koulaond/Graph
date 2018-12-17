package core.schema.assembly;

import core.schema.annotations.Node;
import core.schema.assembly.definitions.NodeDefinition;
import core.schema.assembly.definitions.RelationDefinition;
import core.schema.assembly.definitions.SchemaDefinition;
import core.schema.assembly.definitions.property.PropertyDefinition;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static utils.CommonUtils.uncapitalize;

@Slf4j
public class ModelSchemaAssembler {
  private String name;
  private String basePackage;
  private boolean strict = true;
  private Map<String, Object> additionalInfo;
  private Map<String, NodeBuilder> nodeBuilders;
  private Map<String, RelationBuilder> relationBuilders;

  public ModelSchemaAssembler() {
    // Default values
    additionalInfo = new HashMap<>();
    nodeBuilders = new HashMap<>();
    relationBuilders = new HashMap<>();
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
      throw new IllegalStateException("Schema name not defined.");
    }

    if (basePackage == null || basePackage.trim().isEmpty()) {
      throw new IllegalStateException("Base package not defined.");
    }

    SchemaAssembler schemaAssembler = new SchemaAssembler();

    // TODO collect all @Node classes
    // TODO for each @Node class
    // 1. Collect @Node values
    // 2. Collect all properties
    // 3. Collect all relations

    // 1. and 2.
    Set<Class<?>> nodeCLasses = searchNodeClasses(basePackage);
    if(strict && isEmpty(nodeCLasses)) {
      log.error("No @Node classes found in package {}. This state is not allowed in strict mode.", basePackage);
      throw new IllegalStateException();
    }
    NodeValuesEnhancer nodeValuesEnhancer = new NodeValuesEnhancer();
    NodePropertiesEnhancer nodePropertiesEnhancer = new NodePropertiesEnhancer();
    Set<NodeDefinition> nodeDefinitions = new HashSet<>();
    nodeCLasses.forEach(nodeClass -> {
      NodeBuilder nodeBuilder = new NodeBuilder();
      // Enhance node builder with @Node annotation values
      nodeValuesEnhancer.enhance(nodeClass, nodeBuilder);
      // Enhance node builder with set of properties from the node class
      nodePropertiesEnhancer.enhance(nodeClass, nodeBuilder);
      nodeDefinitions.add(nodeBuilder.build());
    });

    // 3.
    Set<RelationDefinition> relationDefinitions = new HashSet<>();
    nodeCLasses.forEach(nodeClass -> {
      RelationExtractor relationExtractor = new RelationExtractor(nodeClass);
      Set<RelationDefinition> relationDefinitionsForNode = relationExtractor.extractDefinitions();
      relationDefinitions.addAll(relationDefinitionsForNode);
    });

    // Assemble schema
    schemaAssembler.name(name)
            .strict(strict)
            .additionalInfo(additionalInfo);

    SchemaAssembler.NodeCollector nodeCollector = schemaAssembler.defineNodes();
    nodeDefinitions.forEach(nodeCollector::node);
    nodeCollector.finish();

    SchemaAssembler.RelationCollector relationCollector = schemaAssembler.defineRelations();
    relationDefinitions.forEach(relationCollector::relation);
    relationCollector.finish();

    return schemaAssembler.assemble();
  }

  private Set<Class<?>> searchNodeClasses(String basePackage) {
    List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
    classLoadersList.add(ClasspathHelper.contextClassLoader());
    classLoadersList.add(ClasspathHelper.staticClassLoader());

    Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
            .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
            .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(basePackage))));
    Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
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

  private interface Enhancer<T> {

    void enhance(Class<?> nodeClass, T enhancedObject);
  }

  private static class NodeValuesEnhancer implements Enhancer<NodeBuilder> {

    @Override
    public void enhance(Class<?> nodeClass, NodeBuilder nodeBuilder) {
      Node nodeAnnotation = nodeClass.getAnnotation(Node.class);
      String nodeType = uncapitalize(nodeAnnotation.nodeType());
      if (nodeType.isEmpty()) {
        // Default nodeType is uncapitalized class.simpleName()
        nodeType = uncapitalize(nodeClass.getSimpleName());
      }
      nodeBuilder.nodeType(nodeType);
      nodeBuilder.immutable(nodeAnnotation.immutable());
      nodeBuilder.maxCount(nodeAnnotation.maxCount());
    }
  }

  private static class NodePropertiesEnhancer implements Enhancer<NodeBuilder> {

    @Override
    public void enhance(Class<?> nodeClass, NodeBuilder nodeBuilder) {
      PropertyExtractor propertyExtractor = new PropertyExtractor(nodeClass);
      Set<PropertyDefinition> propertyDefinitions = propertyExtractor.extractDefinitions();
      propertyDefinitions.forEach(nodeBuilder::addProperty);
    }
  }
}
