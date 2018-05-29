package repository.schema.metamodel;

import repository.schema.descriptions.PropertyDescription;

import java.util.Set;

public interface RelationDefinition {
    String getRelationType();

    Set<PropertyDescription> getPropertyDescriptions();
}
