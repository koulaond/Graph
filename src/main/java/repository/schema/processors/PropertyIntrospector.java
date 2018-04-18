package repository.schema.processors;

import org.reflections.Reflections;
import repository.schema.annotations.Node;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class PropertyIntrospector {

    public List<PropertyMetadata> introspect(Class clazz){
        if(clazz.getAnnotation(Node.class) == null){
            throw new IllegalArgumentException(String.format("Class %s is not annotated as @Node", clazz.getName()));
        }

    }



}
