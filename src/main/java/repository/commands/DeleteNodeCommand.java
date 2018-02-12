package repository.commands;

import model.Node;

import java.util.UUID;

public class DeleteNodeCommand<T extends Node> implements Command<T> {

    protected UUID nodeUuid;

    public DeleteNodeCommand(UUID nodeUuid) {
        this.nodeUuid = nodeUuid;
    }

    @Override
    public Class<T> getType() {
        return (Class<T>) Node.class;
    }
}
