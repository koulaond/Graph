import model.Builders;
import model.Connection;
import model.Graph;
import model.Node;

public class App {

    public static void main(String[] args) {
        Node node1 = Builders.nodeBuilder()
                .label("Node 1")
                .property("Description", "This is Node 1 description")
                .build();

        Node node2 = Builders.nodeBuilder()
                .label("Node 2")
                .property("Description", "This is Node 2 description")
                .build();

        Connection connection = Builders.connectionBuilder()
                .label("Edge 1")
                .property("Description", "This is the edge connecting Nodes 1 and 2")
                .sourceNode(node1)
                .targetNode(node2)
                .build();

        Graph graph = Builders.graphBuilder()
                .label("Graph")
                .node(node1, true)
                .node(node2)
                .connection(connection)
                .build();
    }
}
