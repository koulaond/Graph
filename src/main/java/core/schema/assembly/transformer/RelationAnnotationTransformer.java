package core.schema.assembly.transformer;

import core.schema.annotations.Relationship;
import core.schema.assembly.PropertyExtractor;
import repository.api.definitions.RelationDefinition;
import repository.api.definitions.property.PropertyDefinition;

import java.util.Set;

public class RelationAnnotationTransformer {

  public RelationDefinition transformToDefinition(Relationship annotation,
                                                  boolean multiValue,
                                                  String startNodeName,
                                                  String endNodeName) {
    Class<?> propertyHolderClass = annotation.propertyHolderClass();
    PropertyExtractor propertyExtractor = new PropertyExtractor(propertyHolderClass);
    Set<PropertyDefinition> propertyDefinitions = propertyExtractor.extractDefinitions();
    RelationDefinition relationDefinition = new RelationDefinition(
            annotation.name(),
            annotation.direction(),
            startNodeName,
            endNodeName,
            multiValue,
            propertyDefinitions);
    return relationDefinition;
  }
}
