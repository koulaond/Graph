package app.testmodel;

import model.Direction;
import core.schema.annotations.Node;
import core.schema.annotations.Relationship;
import core.schema.annotations.properties.StringProperty;

import java.util.Set;

@Node(nodeType = "user")
public class User {

  @StringProperty(name = "userName", nonNull = true)
  private String userName;

  // Single-value relation
  @Relationship(name = "bestMovie")
  private Movie bestMovie;

  // Collection-based multi-value relation
  @Relationship(name = "watchList", propertyHolderClass = WatchListRelation.class, direction = Direction.OUTGOING)
  private Set<Movie> watchList;

  // Array-based multi-value relation
  @Relationship(name = "actors")
  private Actor[] favoriteActors;
}
