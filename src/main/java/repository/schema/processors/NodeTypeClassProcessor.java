package repository.schema.processors;

import repository.schema.annotations.Node;
import repository.schema.descriptions.TypeDescription;

public class NodeTypeClassProcessor {

    public <T> TypeDescription<T> processClass(Class<T> clazz){
        Node nodeAnnotation = clazz.getAnnotation(Node.class);
        String typeName = nodeAnnotation.nodeType();
        boolean immutable = nodeAnnotation.immutable();
        // TODO
        return null;
    }
}
