package core.schema.introspection;

import core.schema.annotations.PropertyHolder;
import core.schema.descriptions.PropertyDescription;
import core.schema.introspection.collector.FieldCollector;
import core.schema.introspection.collector.GetterCollector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static core.schema.introspection.creator.CreatorSupplier.supply;

/**
 * Introspector for processing a class annotated with @{@link PropertyHolder} (class describing a set of properties
 * for some meta-node, i.e. {@link model.Relation}). Behaviour is very similar to {@link NodeIntrospector} except
 * Relation annotations are NOT processed here.
 * @param <T>
 */
public class PropertyHolderIntrospector<T> extends AbstractIntrospector<T, PropertyHolder, Set<PropertyDescription>> {

    public PropertyHolderIntrospector(Class<T> introspectedClass) {
        super(introspectedClass);
    }

    @Override
    public Set<PropertyDescription> introspect() {
        validateClass(PropertyHolder.class);

        Map<String, Field> fieldMap = new FieldCollector().collect(introspectedClass);
        Map<String, Method> getterMap = new GetterCollector().collect(introspectedClass);
        AnnotationIntrospector<Field> fieldAnnotationIntrospector = new AnnotationIntrospector(fieldMap.values());
        AnnotationIntrospector<Method> getterAnnotationIntrospector = new AnnotationIntrospector(getterMap.values());

        Map<Field, Annotation> propertyAnnotationsForFields = fieldAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);
        Map<Method, Annotation> propertyAnnotationsForGetters = getterAnnotationIntrospector.introspectAnnotations(PropertyDeclaration::isPropertyAnnotation);

        Map<String, Annotation> propertyAnnotations = new AnnotationMerger().merge(propertyAnnotationsForFields, propertyAnnotationsForGetters);

        Set<PropertyDescription> propertyDescriptions = new HashSet<>();

        propertyAnnotations.forEach((fieldName, annotation) -> {
            boolean multiValue = Utils.isFieldTypeMultiValue(fieldMap.get(fieldName));
            propertyDescriptions.add(supply(annotation.annotationType()).processProperty(annotation, fieldName, multiValue));
        });
        return propertyDescriptions;
    }
}
