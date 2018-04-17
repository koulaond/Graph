package repository.schema.build;

import org.reflections.Reflections;
import repository.schema.annotations.Node;

import java.util.Set;

public class NodeTypeClassFinder {

    public Set<Class<?>> findClasses(String pckg){
        Reflections reflections = new Reflections(pckg);
        Set<Class<?>> nodeTypeAnnotations = reflections.getTypesAnnotatedWith(Node.class);
        return nodeTypeAnnotations;
    }
}
