package app.testmodel;

import lombok.Getter;
import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.StringProperty;

@Getter
@PropertyHolder
public class FavouriteActorsRelation {

    @StringProperty
    private String personalRank;
}
