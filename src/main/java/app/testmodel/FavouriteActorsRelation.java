package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.PropertyHolder;
import repository.schema.annotations.properties.StringProperty;

@Getter
@PropertyHolder
public class FavouriteActorsRelation {

    @StringProperty
    private String personalRank;
}
