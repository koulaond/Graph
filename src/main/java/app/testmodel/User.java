package app.testmodel;

import repository.schema.Direction;
import repository.schema.annotations.NodeType;
import repository.schema.annotations.Relationship;
import repository.schema.annotations.TextProperty;

import java.util.Set;

@NodeType(typeName = "user")
public class User {

    @TextProperty(nonNull = true)
    private String userName;

    @Relationship(name = "WATCHLIST", relationshipClass = WatchListRelation.class, direction = Direction.OUTGOING)
    private Set<Movie> watchList;


    private Set<Actor> favoriteActors;
}
