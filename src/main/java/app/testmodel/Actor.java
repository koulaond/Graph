package app.testmodel;

import lombok.Getter;
import repository.schema.annotations.NodeType;

@Getter
@NodeType(typeName = "actor")
public class Actor {

    private String firstName, lastName;
    private int age;
}
