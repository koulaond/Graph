package repository.schema.metamodel;

import model.AbstractEntity;
import model.Graph;
import model.Node;

public class MetaNode<T> extends AbstractEntity implements Node<MetaRelation> {

    private Class<T> describedClass;

    private String nodeType;

    private boolean immutable;

    private Long maxCount;

    public MetaNode(Class<T> describedClass, String nodeType, boolean immutable, Long maxCount) {
        this.describedClass = describedClass;
        this.nodeType = nodeType;
        this.immutable = immutable;
        this.maxCount = maxCount;
    }

    @Override
    public boolean includes(Node other) {
        return false;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return false;
    }

    public Class<T> getDescribedClass() {
        return describedClass;
    }

    public String getNodeType() {
        return nodeType;
    }

    public boolean isImmutable() {
        return immutable;
    }

    public Long getMaxCount() {
        return maxCount;
    }
}
