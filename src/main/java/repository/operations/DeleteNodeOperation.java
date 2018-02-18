package repository.operations;

import model.Node;

import java.util.UUID;

public class DeleteNodeOperation<T extends Node> implements Operation<T> {

    protected UUID nodeUuid;

    public DeleteNodeOperation(UUID nodeUuid) {
        this.nodeUuid = nodeUuid;
    }

    @Override
    public Class<T> getType() {
        return (Class<T>) Node.class;
    }
}
