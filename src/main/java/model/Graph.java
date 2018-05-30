package model;

import java.util.UUID;

public class Graph implements HasId {

    private UUID id;

    private String name;

    private String description;

    private Node initialNode;

    public Graph(UUID id, String name, String description, Node initialNode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialNode = initialNode;
    }

    public Graph(UUID id, String name, String description) {
        this(id, name, description, null);
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Node getInitialNode() {
        return this.initialNode;
    }
}
