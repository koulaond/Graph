package app.testmodel;

import core.schema.annotations.Node;
import core.schema.annotations.properties.NumericProperty;
import core.schema.annotations.properties.StringProperty;

import java.util.Set;

@Node(nodeType = "movie")
public class Movie {

    @StringProperty(nonNull = true)
    private String name;

    @NumericProperty(nonNull = true, minValue = 1890, maxValue = 2050, immutable = true)
    private int year;


    private Set<Actor> actors;
}
