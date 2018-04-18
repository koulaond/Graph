package repository.schema.metamodel;

import model.Graph;
import model.Node;

public class MetaNode<T> extends AbstractMetaEntity implements Node<MetaRelation> {

    private Class<T> describedClass;

    private boolean immutable;

    private Long maxCount;

    public MetaNode(String type, Class<T> describedClass, boolean immutable, Long maxCount) {
        super(type);
        this.describedClass = describedClass;
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

    public boolean isImmutable() {
        return immutable;
    }

    public Long getMaxCount() {
        return maxCount;
    }
}
