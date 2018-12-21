package examples.testmodel;

import core.schema.annotations.PropertyHolder;
import core.schema.annotations.properties.StringProperty;

@PropertyHolder
public class PlayingRelation {

    @StringProperty(name="character", nonNull = true)
    private String character;

    // Property annotation on getter overrides annotation on field
    @StringProperty(name = "character", nonNull = false)
    public String getCharacter() {
        return character;
    }
}
