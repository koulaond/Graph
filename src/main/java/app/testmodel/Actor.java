package app.testmodel;

import core.schema.annotations.Node;
import core.schema.annotations.Relationship;
import core.schema.annotations.properties.StringProperty;
import model.Direction;

import java.util.Set;

@Node(nodeType = "actor")
public class Actor extends Contributor {

    // Overrides property from Contributor superclass
    @StringProperty(name = "firstName", maxLength = 255, immutable = true, nonNull = true)
    protected String firstName;

    @Relationship(name = "movies")
    private Set<Movie> movies;

    // It should be equal to Director.inContribution
    @Relationship(name = "inContribution")
    private Director inContribution;


    // It should be equal to Director.inContributionDirected
    @Relationship(name = "inContributionDirected", direction = Direction.OUTGOING)
    private Director inContributionDirected;

    // Overriding field annotation defined above
    @Relationship(name = "movies", nonNull = true)
    public Set<Movie> getMovies() {
        return movies;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }
}
