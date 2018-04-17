package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.Node;

@Getter
@Node(nodeType = "actor")
public class Actor {

    private String firstName, lastName;
    private int age;
}
