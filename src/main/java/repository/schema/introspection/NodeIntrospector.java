package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.Relationship;
import repository.schema.descriptions.NodeDescription;
import repository.schema.descriptions.PropertyDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.introspection.creator.RelationshipDescriptionCreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static repository.schema.introspection.creator.CreatorSupplier.supply;

/**
 * Introspector for processing a class annotated with @{@link model.Node} (schema describing model class that defines structure of
 * the Node type in repository). The introspector returns {@link NodeDescription} result object that contains various
 * {@link PropertyDescription}s and @{@link model.Relation}s for each defined @Property annotated field or getter.
 * Class is introspected with following rules:
 * 1. Property/relation defined on getter overrides property/relation defined on field
 * 2. Property/relation defined on overriden getter overrides property/relation defined on getter implemented in superclass
 * 3. Property/relation defined on overriden field overrides property/relation defined on field implemented in superclass
 * @param <T> introspected class type
 */
public class NodeIntrospector<T> extends Introspector<T, Node, NodeDescription<T>>{

    public NodeIntrospector(Class<T> introspectedClass) {
        super(introspectedClass);
    }

    @Override
    public NodeDescription<T> introspect() {
        // @Node annotations check
        validateClass(Node.class);
        Node nodeAnnotation = introspectedClass.getAnnotation(Node.class);
        // Load values from Node annotation
        boolean immutable = nodeAnnotation.immutable();
        String nodeType = nodeAnnotation.nodeType();
        long maxCount = nodeAnnotation.maxCount();

        // Find all fields and getters
        Map<String, Field> fieldMap = new FieldCollector().collect(introspectedClass);
        Map<String, Method> getterMap = new GetterCollector().collect(introspectedClass);
        AnnotationIntrospector<Field> fieldAnnotationIntrospector = new AnnotationIntrospector(fieldMap);
        AnnotationIntrospector<Method> getterAnnotationIntrospector = new AnnotationIntrospector<>(getterMap);

        // Find properties and relations on fields
        Map<Field, Annotation> propertyAnnotationsForFields = fieldAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);
        Map<Field, Annotation> relationAnnotationsForFields = fieldAnnotationIntrospector.introspectAnnotations(annotation -> Relationship.class.equals(annotation));

        // Find properties and relations on getters
        Map<Method, Annotation> propertyAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);
        Map<Method, Annotation> relationAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(annotation -> Relationship.class.equals(annotation));

        // Merge annotations on fields and getters -> Annotations on getters override annotations on fields
        AnnotationMerger merger = new AnnotationMerger();
        Map<Field, Annotation> propertyAnnotations = merger.merge(propertyAnnotationsForFields, propertyAnnotationsForGetters);
        Map<Field, Relationship> relationAnnotations = merger.merge(relationAnnotationsForFields, relationAnnotationsForGetters);

        // Create PropertyDescription and RelationshipDescription sets
        Set<PropertyDescription> propertyDescriptions = new HashSet<>();
        Set<RelationshipDescription> relationshipDescriptions = new HashSet<>();

        propertyAnnotations.forEach((field, annotation) -> {
            boolean multiValue = Collection.class.isAssignableFrom(field.getType());
            propertyDescriptions.add(supply(annotation.annotationType()).processProperty(annotation, multiValue));
        });

        RelationshipDescriptionCreator creator = new RelationshipDescriptionCreator();
        relationAnnotations.forEach((field, annotation) -> {
            boolean multiValue = Collection.class.isAssignableFrom(field.getType());
            relationshipDescriptions.add(creator.processProperty(annotation, multiValue));
        });
        NodeDescription nodeDescription = new NodeDescription(
                introspectedClass,
                nodeType,
                immutable,
                maxCount,
                propertyDescriptions,
                relationshipDescriptions);
        return nodeDescription;
    }


}
