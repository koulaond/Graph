package repository.operations;

import model.Node;

public class UpdateNodeOperation<T extends Node> extends ChangeNodeOperation<T> {

    protected T newData;

    public UpdateNodeOperation(T data, T newData) {
        super(data);
        this.newData = newData;
    }
}
