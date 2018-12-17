package core.schema.assembly;

import core.schema.annotations.Node;
import core.schema.annotations.Relationship;
import core.schema.assembly.definitions.RelationDefinition;
import core.schema.assembly.transformer.RelationAnnotationTransformer;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

@Slf4j
public class RelationExtractor extends AbstractDefinitionExtractor<RelationDefinition, Relationship> {

  public RelationExtractor(Class<?> nodeClass) {
    super(nodeClass);
  }

  @Override
  protected RelationDefinition doTransform(Relationship annotation, Class<?> elementType) {
    Node startNodeAnnotation = nodeClass.getAnnotation(Node.class);
    if (startNodeAnnotation == null) {
      log.error("Start @Node annotation not present on node class {}, where is expected", nodeClass.getName());
      throw new IllegalArgumentException();
    }
    boolean isMultiValue = false;
    String startNodeName = startNodeAnnotation.nodeType();
    String endNodeName;
    Node endNodeAnnotation;
    if (isMultiValue(elementType)) {
      isMultiValue = true;
      Class<?> endNodeClass = elementType.getComponentType();
      endNodeAnnotation = endNodeClass.getAnnotation(Node.class);
    } else {
      endNodeAnnotation = elementType.getAnnotation(Node.class);
    }
    if (endNodeAnnotation == null) {
      log.error("End @Node annotation not present on end node class, where is expected");
      throw new IllegalArgumentException();
    }
    endNodeName = endNodeAnnotation.nodeType();
    RelationAnnotationTransformer transformer = new RelationAnnotationTransformer();
    return transformer.transformToDefinition(annotation, isMultiValue, startNodeName, endNodeName);
  }

  @Override
  protected boolean isAnnotationOfType(Annotation annotation) {
    return Relationship.class.equals(annotation.annotationType());
  }
}
