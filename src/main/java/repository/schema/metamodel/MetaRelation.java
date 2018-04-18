package repository.schema.metamodel;

import model.Direction;
import model.Graph;
import model.Relation;

public class MetaRelation extends AbstractMetaEntity implements Relation<MetaNode> {

    private Class propertyHolderClass;

    public MetaRelation(String type, Class propertyHolderClass) {
        super(type);
        this.propertyHolderClass = propertyHolderClass;
    }

    @Override
    public MetaNode getSourceNode() {
        return null;
    }

    @Override
    public MetaNode getTargetNode() {
        return null;
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public boolean isIncludedIn(Graph graph) {
        return false;
    }

    public Class getPropertyHolderClass() {
        return propertyHolderClass;
    }
}
