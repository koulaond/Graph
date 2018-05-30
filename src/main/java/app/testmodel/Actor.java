package app.testmodel;

import core.schema.annotations.Node;

@Node(nodeType = "actor")
public class Actor {

    private String firstName, lastName;
    private int age;
}
