package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.properties.EnumProperty;
import repository.schema.annotations.PropertyHolder;
import repository.schema.annotations.properties.TextProperty;

@Getter
@PropertyHolder
public class WatchListRelation {

    @EnumProperty(nonNull = true)
    private WatchListState state;

    @TextProperty
    private String note;
}
