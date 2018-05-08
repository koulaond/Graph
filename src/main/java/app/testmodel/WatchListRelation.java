package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.properties.EnumProperty;
import repository.schema.annotations.PropertyHolder;
import repository.schema.annotations.properties.StringProperty;

@Getter
@PropertyHolder
public class WatchListRelation {

    @EnumProperty(nonNull = true)
    private WatchListState state;

    @StringProperty
    private String note;
}
