package app.testmodel;

import lombok.Getter;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.StringProperty;

@Getter
@PropertyHolder
public class WatchListRelation {

    @EnumProperty(nonNull = true, enumClass = WatchListState.class)
    private WatchListState state;

    @StringProperty
    private String note;
}
