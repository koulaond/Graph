package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.RelationshipClass;
import repository.schema.annotations.TextProperty;

@Getter
@RelationshipClass
public class PlayingRelation {

    @TextProperty(nonNull = true)
    private String character;
}
