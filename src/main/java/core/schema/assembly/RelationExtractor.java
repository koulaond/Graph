package core.schema.assembly;

import core.schema.annotations.Node;
import core.schema.annotations.Relationship;
import repository.api.definitions.RelationDefinition;
import core.schema.assembly.transformer.RelationAnnotationTransformer;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Collection;

import static utils.CommonUtils.uncapitalize;

@Slf4j
public class RelationExtractor extends AbstractDefinitionExtractor<RelationDefinition, Relationship> {

  public RelationExtractor(Class<?> nodeClass) {
    super(nodeClass);
  }

  @Override
  protected RelationDefinition doTransform(Relationship annotation, Class<?> elementType, Class<?>... genericTypes) {
    Node startNodeAnnotation = nodeClass.getAnnotation(Node.class);
    if (startNodeAnnotation == null) {
      log.error("Start @Node annotation not present on node class {}, where is expected", nodeClass.getName());
      throw new IllegalArgumentException();
    }
    boolean isMultiValue = false;
    String startNodeName = uncapitalize(startNodeAnnotation.nodeType());
    String endNodeName;
    Node endNodeAnnotation;
    if (isMultiValue(elementType)) {
      isMultiValue = true;
      Class<?> endNodeClass;
      if (Collection.class.isAssignableFrom(elementType)) {
        if (genericTypes.length > 0) {
          endNodeClass = genericTypes[0];
        } else {
          log.error("End node class is generic collection but no generic type is present on input.");
          throw new IllegalStateException();
        }
      } else {
        endNodeClass = elementType.getComponentType();
      }
      endNodeAnnotation = endNodeClass.getAnnotation(Node.class);
    } else {
      endNodeAnnotation = elementType.getAnnotation(Node.class);
    }
    if (endNodeAnnotation == null) {
      log.error("End @Node annotation not present on end node class, where is expected");
      throw new IllegalArgumentException();
    }
    endNodeName = uncapitalize(endNodeAnnotation.nodeType());
    RelationAnnotationTransformer transformer = new RelationAnnotationTransformer();
    return transformer.transformToDefinition(annotation, isMultiValue, startNodeName, endNodeName);
  }

  @Override
  protected boolean isAnnotationOfType(Annotation annotation) {
    return Relationship.class.equals(annotation.annotationType());
  }
}
