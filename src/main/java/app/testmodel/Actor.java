package app.testmodel;

import lombok.Getter;
import core.schema.annotations.Node;

@Getter
@Node(nodeType = "actor")
public class Actor {

    private String firstName, lastName;
    private int age;
}
