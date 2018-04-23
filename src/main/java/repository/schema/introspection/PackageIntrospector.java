package repository.schema.introspection;

import org.reflections.Reflections;
import repository.schema.annotations.Node;

import java.util.Set;

public class PackageIntrospector {

    public Set<Class<?>> introspect(String pckg){
        Reflections reflections = new Reflections(pckg);
        Set<Class<?>> nodeTypeAnnotations = reflections.getTypesAnnotatedWith(Node.class);
        return nodeTypeAnnotations;
    }
}
