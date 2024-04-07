import ca.mcmaster.se2aa4.mazerunner.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GraphBuilderTest {

    private final Maze maze = new Maze("./examples/tiny.maz.txt");

    private Graph graph;

    public GraphBuilderTest() throws IOException {
    }

    @BeforeEach
    void setUp(){
        GraphBuilder graphBuilder = new GraphBuilder(maze);
        graph = graphBuilder.build();
    }

    @Test
    void nodes(){
        int numberOfNodes = 15;
        Assertions.assertEquals(numberOfNodes, graph.nodes().size());
    }

    @Test
    void edges(){
       ;
    }
}
