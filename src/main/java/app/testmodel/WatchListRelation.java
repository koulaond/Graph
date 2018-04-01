package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.EnumProperty;
import repository.schema.annotations.RelationshipClass;
import repository.schema.annotations.TextProperty;

@Getter
@RelationshipClass
public class WatchListRelation {

    @EnumProperty(nonNull = true)
    private WatchListState state;

    @TextProperty
    private String note;
}
