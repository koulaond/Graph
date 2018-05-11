package repository.schema.introspection;

import repository.schema.annotations.PropertyHolder;
import repository.schema.descriptions.PropertyDescription;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static repository.schema.introspection.creator.CreatorSupplier.supply;

public class PropertyHolderIntrospector<T> extends Introspector<T, PropertyHolder, Set<PropertyDescription>> {


    public PropertyHolderIntrospector(Class<T> introspectedClass) {
        super(introspectedClass);
    }

    @Override
    public Set<PropertyDescription> introspect() {
        validateClass(PropertyHolder.class);

        Map<String, Field> fieldMap = new FieldCollector().collect(introspectedClass);
        Map<String, Method> getterMap = new GetterCollector().collect(introspectedClass);
        AnnotationIntrospector<Field> fieldAnnotationIntrospector = new AnnotationIntrospector(fieldMap);
        AnnotationIntrospector<Method> getterAnnotationIntrospector = new AnnotationIntrospector<>(getterMap);

        Map<Field, Annotation> propertyAnnotationsForFields = fieldAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);
        Map<Method, Annotation> propertyAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);

        Map<Field, Annotation> propertyAnnotations = new AnnotationMerger().merge(propertyAnnotationsForFields, propertyAnnotationsForGetters);

        Set<PropertyDescription> propertyDescriptions = new HashSet<>();

        propertyAnnotations.forEach((field, annotation) -> {
            boolean multiValue = Collection.class.isAssignableFrom(field.getType());
            propertyDescriptions.add(supply(annotation.annotationType()).processProperty(annotation, multiValue));
        });
        return propertyDescriptions;
    }
}
