package repository.schema.metamodel;

import model.AbstractEntity;

public abstract class AbstractMetaEntity extends AbstractEntity  {
    private String type;

    public AbstractMetaEntity(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
