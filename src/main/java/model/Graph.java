package model;

public class Graph implements HasId {

    private Long id;

    private String name;

    private String description;

    private Node initialNode;

    public Graph(Long id, String name, String description, Node initialNode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialNode = initialNode;
    }

    public Graph(Long id, String name, String description) {
        this(id, name, description, null);
    }

    @Override
    public Long getId() {
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
