package app.testmodel;

import repository.schema.Direction;
import repository.schema.annotations.Node;
import repository.schema.annotations.Relationship;
import repository.schema.annotations.properties.StringProperty;

import java.util.Set;

@Node(nodeType = "user")
public class User {

    @StringProperty(nonNull = true)
    private String userName;

    @Relationship(name = "WATCHLIST", propertyHolderClass = WatchListRelation.class, direction = Direction.OUTGOING, referencedClass = Movie.class)
    private Set<Movie> watchList;


    private Set<Actor> favoriteActors;
}
