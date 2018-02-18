package model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

public abstract class AbstractItem implements GraphElement {

    @NonNull
    @Getter
    protected UUID uuid = UUID.randomUUID();

    @Override
    public boolean equals(Object that) {
        if (super.equals(that)) {
            return true;
        }
        if (!(that instanceof AbstractItem)) {
            return false;
        }
        AbstractItem thatItem = (AbstractItem) that;
        return this.getUuid().equals(thatItem.getUuid());
    }
}
