package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.annotations.Relation;
import repository.schema.descriptions.PropertyDescription;
import repository.schema.descriptions.RelationshipDescription;
import repository.schema.descriptions.TypeDescription;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static java.util.stream.Stream.of;

public class Introspector<T> {


    private Class<T> introspectedClass;

    private String typeName;

    private boolean immutable;

    private Set<PropertyDescription> propertyDescriptions;

    private Set<RelationshipDescription> relationshipDescriptions;

    public Introspector(Class<T> introspectedClass) {
        this.introspectedClass = introspectedClass;
    }

    public TypeDescription<T> introspect() {
        TypeDescription<T> typeDescription = new TypeDescription<>();
        // Class must be a node
        if (introspectedClass.getAnnotation(Node.class) == null) {
            throw new IllegalArgumentException(format("Class %s is not annotated as @Node", introspectedClass.getName()));
        }
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
        return typeDescription;
    }


}
