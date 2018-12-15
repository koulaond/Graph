package app.testmodel;

import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.EnumProperty;
import core.schema.annotations.properties.StringProperty;

@PropertyHolder
public class WatchListRelation {

    @EnumProperty(name = "state", nonNull = true, enumClass = WatchListState.class)
    private WatchListState state;

    @StringProperty(name = "note")
    private String note;
}
