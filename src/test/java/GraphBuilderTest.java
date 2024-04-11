import ca.mcmaster.se2aa4.mazerunner.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class GraphBuilderTest {

    private final Maze maze = new Maze("./examples/tiny.maz.txt");

    private final Maze maze2 = new Maze("./examples/small.maz.txt");

    private Graph graphTiny;

    private Graph graphSmall;

    private List<Node> nodesTiny;

    private List<Node> nodesSmall;

    public GraphBuilderTest() throws IOException {
    }

    @BeforeEach
    void setUp(){
        GraphBuilder graphBuilderTiny = new GraphBuilder(maze);
        graphTiny = graphBuilderTiny.build();
        nodesTiny = graphTiny.nodes();
        GraphBuilder graphBuilderSmall = new GraphBuilder(maze2);
        graphSmall = graphBuilderSmall.build();
        nodesSmall = graphSmall.nodes();
    }

    @Test
    void nodesTiny(){
        int numberOfNodes = 15;
        Assertions.assertEquals(numberOfNodes, nodesTiny.size());
    }

    @Test
    void edgesTiny(){
        int endEdges = 4;
        Assertions.assertEquals(endEdges, graphTiny.edgeList(nodesTiny.get(nodesTiny.size() -1)).size());
    }

    @Test
    void nodesSmall(){
        int numberOfNodes = 42;
        Assertions.assertEquals(numberOfNodes, nodesSmall.size());
    }

    @Test
    void edgesSmall(){
        int endEdges = 1;
        Assertions.assertEquals(endEdges, graphSmall.edgeList(nodesSmall.get(nodesSmall.size() -1)).size());
    }
}
