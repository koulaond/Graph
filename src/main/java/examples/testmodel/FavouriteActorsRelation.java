package examples.testmodel;

import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.StringProperty;

@PropertyHolder
public class FavouriteActorsRelation {

    @StringProperty(name = "personalRank")
    private String personalRank;
}
