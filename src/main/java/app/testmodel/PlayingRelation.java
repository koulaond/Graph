package app.testmodel;

import repository.schema.annotations.PropertyHolder;
import repository.schema.annotations.properties.TextProperty;

@PropertyHolder
public class PlayingRelation {

    @TextProperty(nonNull = true)
    private String character;

    // Property annotation on getter overrides annotation on field
    @TextProperty(nonNull = false)
    public String getCharacter() {
        return character;
    }
}
