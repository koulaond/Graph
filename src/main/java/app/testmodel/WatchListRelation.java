package app.testmodel;

import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.StringProperty;

@PropertyHolder
public class WatchListRelation {

    @EnumProperty(nonNull = true, enumClass = WatchListState.class)
    private WatchListState state;

    @StringProperty
    private String note;
}
