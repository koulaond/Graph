package app.testmodel;

import model.Direction;
import core.schema.annotations.Node;
import core.schema.annotations.Relationship;
import core.schema.annotations.properties.StringProperty;

import java.util.Set;

@Node(nodeType = "user")
public class User {

    @StringProperty(nonNull = true)
    private String userName;

    @Relationship(name = "WATCHLIST", propertyHolderClass = WatchListRelation.class, direction = Direction.OUTGOING, referencedClass = Movie.class)
    private Set<Movie> watchList;


    private Set<Actor> favoriteActors;
}
