package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.PropertyHolder;
import repository.schema.annotations.TextProperty;

@Getter
@PropertyHolder
public class FavouriteActorsRelation {

    @TextProperty
    private String personalRank;
}
