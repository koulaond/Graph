package repository.commands;

import model.Node;

public class UpdateNodeCommand<T extends Node> extends ChangeNodeCommand<T> {

    protected T newData;

    public UpdateNodeCommand(T data, T newData) {
        super(data);
        this.newData = newData;
    }
}
