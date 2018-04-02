package repository.schema.build;

import org.reflections.Reflections;
import repository.schema.annotations.NodeType;

import java.util.Set;

public class NodeTypeClassFinder {

    public Set<Class<?>> findClasses(String pckg){
        Reflections reflections = new Reflections(pckg);
        Set<Class<?>> nodeTypeAnnotations = reflections.getTypesAnnotatedWith(NodeType.class);
        return nodeTypeAnnotations;
    }
}
