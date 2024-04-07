import ca.mcmaster.se2aa4.mazerunner.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class GraphBuilderTest {

    private final Maze maze = new Maze("./examples/tiny.maz.txt");

    private Graph graph;

    private List<Node> nodes;

    public GraphBuilderTest() throws IOException {
    }

    @BeforeEach
    void setUp(){
        GraphBuilder graphBuilder = new GraphBuilder(maze);
        graph = graphBuilder.build();
        nodes = graph.nodes();
    }

    @Test
    void nodes(){
        int numberOfNodes = 15;
        Assertions.assertEquals(numberOfNodes, nodes.size());
    }

    @Test
    void edges(){
        int endEdges = 4;
        Assertions.assertEquals(endEdges, graph.edgeList(nodes.get(nodes.size() -1)).size());
    }
}
