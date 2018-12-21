package examples.testmodel;

import core.schema.annotations.Node;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;

@Node(nodeType = "movie")
public class Movie {

    @StringProperty(name = "name", nonNull = true)
    private String name;

    @NumericProperty(name = "year", nonNull = true, minValue = 1890, maxValue = 2050, immutable = true)
    private int year;

}
