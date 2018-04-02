package repository.schema.build;

import org.reflections.Reflections;
import repository.schema.annotations.NodeType;
import repository.schema.descriptions.TypeDescription;

public class NodeTypeClassProcessor {

    public <T> TypeDescription<T> processClass(Class<T> clazz){
        NodeType nodeTypeAnnotation = clazz.getAnnotation(NodeType.class);
        String typeName = nodeTypeAnnotation.typeName();
        boolean immutable = nodeTypeAnnotation.immutable();
        // TODO
        return null;
    }
}
