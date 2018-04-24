package repository.schema.introspection;

import repository.schema.annotations.Node;
import repository.schema.metamodel.MetaNode;
import static java.lang.String.format;

public class NodeClassIntrospector {

    public <T> MetaNode<T> introspectNodeClass(Class<T> clazz) {
        Node node = clazz.getAnnotation(Node.class);
        if(node == null){
            throw new IllegalStateException(
                    format(
                            "Cannot obtain @Node annotation on class %s",
                            clazz.getName()
                    )
            );
        }
        MetaNode<T> metaNode = new MetaNode<>(node.nodeType(), clazz, node.immutable(), node.maxCount());
        return metaNode;
    }
}
