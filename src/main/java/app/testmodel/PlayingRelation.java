package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.PropertyHolder;
import repository.schema.annotations.TextProperty;

@Getter
@PropertyHolder
public class PlayingRelation {

    @TextProperty(nonNull = true)
    private String character;
}
