package repository.operations;

import model.Entity;

public abstract class ChangeNodeOperation<T extends Entity> implements Operation<T> {

    protected T data;

    public ChangeNodeOperation(T data) {
        this.data = data;
    }

    @Override
    public Class<T> getType() {
        return (Class<T>) data.getClass();
    }
}
