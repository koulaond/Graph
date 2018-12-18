package app.testmodel;

import core.schema.annotations.Node;
import core.schema.annotations.Relationship;
import model.Direction;

import java.util.Set;

@Node(nodeType = "Director")
public class Director extends Contributor {

  private Set<Movie> directing;

  // It should be equal to Actor.inContribution
  @Relationship(name = "inContribution")
  private Actor inContribution;

  // It should be equal to Director.inContributionDirected
  @Relationship(name = "inContributionDirected", direction = Direction.INCOMING)
  private Director inContributionDirected;

  @Relationship(name = "directing")
  public Set<Movie> getDirecting() {
    return directing;
  }


}
