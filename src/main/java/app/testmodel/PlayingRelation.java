package app.testmodel;

import repository.schema.annotations.PropertyHolder;
import repository.schema.annotations.properties.StringProperty;

@PropertyHolder
public class PlayingRelation {

    @StringProperty(nonNull = true)
    private String character;

    // Property annotation on getter overrides annotation on field
    @StringProperty(nonNull = false)
    public String getCharacter() {
        return character;
    }
}
