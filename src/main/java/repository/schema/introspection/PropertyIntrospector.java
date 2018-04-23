package repository.schema.introspection;

import repository.schema.annotations.Node;

import java.util.List;

public class PropertyIntrospector {

    public List<PropertyMetadata> introspect(Class clazz){
        if(clazz.getAnnotation(Node.class) == null){
            throw new IllegalArgumentException(String.format("Class %s is not annotated as @Node", clazz.getName()));
        }

    }



}
