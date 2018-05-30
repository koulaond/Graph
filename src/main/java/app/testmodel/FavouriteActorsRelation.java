package app.testmodel;

import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.StringProperty;

@PropertyHolder
public class FavouriteActorsRelation {

    @StringProperty
    private String personalRank;
}
