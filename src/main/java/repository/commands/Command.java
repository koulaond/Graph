package repository.commands;

import model.Entity;

public interface Command<T extends Entity> {

    Class<T> getType();
}
