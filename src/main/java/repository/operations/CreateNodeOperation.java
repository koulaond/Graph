package repository.operations;

import model.Node;

public class CreateNodeOperation<T extends Node> extends ChangeNodeOperation<T> {

    public CreateNodeOperation(T data) {
        super(data);
    }
}
