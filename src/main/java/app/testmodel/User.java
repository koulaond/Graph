package app.testmodel;

import repository.schema.Direction;
import repository.schema.annotations.Node;
import repository.schema.annotations.Relation;
import repository.schema.annotations.TextProperty;

import java.util.Set;

@Node(nodeType = "user")
public class User {

    @TextProperty(nonNull = true)
    private String userName;

    @Relation(name = "WATCHLIST", relationshipClass = WatchListRelation.class, direction = Direction.OUTGOING)
    private Set<Movie> watchList;


    private Set<Actor> favoriteActors;
}
