package core.schema.metamodel;

import core.schema.descriptions.PropertyDescription;

import java.util.Set;

public interface RelationDefinition {
    String getRelationType();

    Set<PropertyDescription> getPropertyDescriptions();
}
