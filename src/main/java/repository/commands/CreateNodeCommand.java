package repository.commands;

import model.Node;

public class CreateNodeCommand<T extends Node> extends ChangeNodeCommand<T> {

    public CreateNodeCommand(T data) {
        super(data);
    }
}
