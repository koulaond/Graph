package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.Relation;
import repository.schema.descriptions.NodeDescription;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import static java.lang.String.format;
import static repository.schema.introspection.Constants.ERROR_DUPLICATE_ANNOTATIONS;
import static repository.schema.introspection.Constants.ERROR_MISSING_ANNOTATION;

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
        Map<Field, Annotation> relationAnnotationsForFields = fieldAnnotationIntrospector.introspectAnnotations(annotation -> Relation.class.equals(annotation));

        Map<Method, Annotation> propertyAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);
        Map<Method, Annotation> relationAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(annotation -> Relation.class.equals(annotation));

        AnnotationMerger merger = new AnnotationMerger();
        Map<String, Annotation> propertyAnnotations = merger.merge(propertyAnnotationsForFields, propertyAnnotationsForGetters);
        Map<String, Annotation> relationAnnotations = merger.merge(relationAnnotationsForFields, relationAnnotationsForGetters);

        // TODO
        return null;
    }


}
