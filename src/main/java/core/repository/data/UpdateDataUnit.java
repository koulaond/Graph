package core.repository.data;

import java.io.Serializable;

public class UpdateDataUnit<T extends Serializable> extends DataUnit<T> {
    private T origin;

    public UpdateDataUnit(String propertyName, T value, T origin) {
        super(propertyName, value);
        this.origin = origin;
    }

    public T getOrigin() {
        return origin;
    }
}
