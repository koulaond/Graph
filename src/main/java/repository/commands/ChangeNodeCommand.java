package repository.commands;

import model.Entity;

public class ChangeNodeCommand<T extends Entity> implements Command<T> {

    protected T data;

    public ChangeNodeCommand(T data) {
        this.data = data;
    }

    @Override
    public Class<T> getType() {
        return (Class<T>) data.getClass();
    }
}
