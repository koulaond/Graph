package app.testmodel;

import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.StringProperty;

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
