package core.repository.data;

import java.io.Serializable;

public class UpdateResultData<T extends Serializable> extends ResultData<T> {
    private T origin;

    public UpdateResultData(String propertyName, T value, T origin) {
        super(propertyName, value);
        this.origin = origin;
    }

    public T getOrigin() {
        return origin;
    }
}
