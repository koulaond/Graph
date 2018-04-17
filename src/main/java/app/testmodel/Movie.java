package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.Node;
import repository.schema.annotations.NumericProperty;
import repository.schema.annotations.TextProperty;

import java.util.Set;

@Getter
@Node(nodeType = "movie")
public class Movie {

    @TextProperty(nonNull = true)
    private String name;

    @NumericProperty(nonNull = true, minValue = 1890, maxValue = 2050, immutable = true)
    private int year;


    private Set<Actor> actors;
}
