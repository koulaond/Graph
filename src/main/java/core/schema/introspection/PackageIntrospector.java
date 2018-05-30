package core.schema.introspection;

import org.reflections.Reflections;
import core.schema.annotations.Node;
import core.schema.descriptions.NodeDescription;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple introspector class that introspects the package, finds all classes with @{@link Node} annotation
 * and for each class creates @{@link NodeDescription} objects.
 */
public class PackageIntrospector {

    /**
     * Receives the package name and processes each @{@link Node} annotated class using @{@link NodeIntrospector}.
     * @param pckg package to ge introspected
     * @return set of @{@link NodeDescription} instances =
     */
    public Set<NodeDescription> introspectPackage(String pckg) {
        Set<Class<?>> nodeClasses = getClasses(pckg);
        return nodeClasses.stream()
                .map(nodeClass -> new NodeIntrospector<>(nodeClass).introspect())
                .collect(Collectors.toSet());
    }

    protected Set<Class<?>> getClasses(String pckg) {
        Reflections reflections = new Reflections(pckg);
        return reflections.getTypesAnnotatedWith(Node.class);
    }
}
