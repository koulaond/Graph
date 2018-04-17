package repository.schema.metamodel;

import model.AbstractEntity;
import model.Direction;
import model.Graph;
import model.Relation;

public class MetaRelation extends AbstractEntity implements Relation<MetaNode> {

    private String name;

    private Class propertyHolderClass;

    public MetaRelation(String name, Class propertyHolderClass) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public Class getPropertyHolderClass() {
        return propertyHolderClass;
    }
}
