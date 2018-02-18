package repository.operations;

import model.Entity;

public interface Operation<T extends Entity> {

    Class<T> getType();
}
