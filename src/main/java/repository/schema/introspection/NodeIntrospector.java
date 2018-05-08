package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.Relationship;
import repository.schema.descriptions.NodeDescription;
import repository.schema.descriptions.PropertyDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.introspection.processor.ProcessorSupplier;
import repository.schema.introspection.processor.RelationshipDescriptionProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static repository.schema.introspection.processor.ProcessorSupplier.supply;

public class NodeIntrospector<T> extends Introspector<T, Node, NodeDescription<T>>{

    public NodeIntrospector(Class<T> introspectedClass) {
        super(introspectedClass);
    }

    @Override
    public NodeDescription<T> introspect() {
        // @Node annotations check
        validateClass(Node.class);
        Node nodeAnnotation = introspectedClass.getAnnotation(Node.class);
        boolean immutable = nodeAnnotation.immutable();
        String nodeType = nodeAnnotation.nodeType();
        long maxCount = nodeAnnotation.maxCount();

        Map<String, Field> fieldMap = new FieldCollector().collect(introspectedClass);
        Map<String, Method> getterMap = new GetterCollector().collect(introspectedClass);
        AnnotationIntrospector<Field> fieldAnnotationIntrospector = new AnnotationIntrospector(fieldMap);
        AnnotationIntrospector<Method> getterAnnotationIntrospector = new AnnotationIntrospector<>(getterMap);

        Map<Field, Annotation> propertyAnnotationsForFields = fieldAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);
        Map<Field, Annotation> relationAnnotationsForFields = fieldAnnotationIntrospector.introspectAnnotations(annotation -> Relationship.class.equals(annotation));

        Map<Method, Annotation> propertyAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);
        Map<Method, Annotation> relationAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(annotation -> Relationship.class.equals(annotation));

        AnnotationMerger merger = new AnnotationMerger();
        Map<Field, Annotation> propertyAnnotations = merger.merge(propertyAnnotationsForFields, propertyAnnotationsForGetters);
        Map<Field, Relationship> relationAnnotations = merger.merge(relationAnnotationsForFields, relationAnnotationsForGetters);

        Set<PropertyDescription> propertyDescriptions = new HashSet<>();
        Set<RelationshipDescription> relationshipDescriptions = new HashSet<>();
        propertyAnnotations.forEach((field, annotation) -> {
            boolean multiValue = Collection.class.isAssignableFrom(field.getType());
            propertyDescriptions.add(supply(annotation.annotationType()).processProperty(annotation, multiValue));
        });

        relationAnnotations.forEach((field, annotation) -> {
            boolean multiValue = Collection.class.isAssignableFrom(field.getType());
            RelationshipDescriptionProcessor processor = new RelationshipDescriptionProcessor();
            relationshipDescriptions.add(processor.processProperty(annotation, multiValue));
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
