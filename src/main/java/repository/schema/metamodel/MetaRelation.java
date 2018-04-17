package repository.schema.metamodel;

import model.AbstractEntity;
import model.Direction;
import model.Graph;
import model.Relation;

public class MetaRelation extends AbstractEntity implements Relation<MetaNode> {
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
}
