import ca.mcmaster.se2aa4.mazerunner.GraphSolution;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GraphSolutionTest {

    private Maze mazeTiny;

    private Maze mazeSmall;

    private GraphSolution graph;

    private final String tinyShortestPath = "3FL4FR3F";

    private final String smallShortestPath = "FLFR2FL6FR4FR2FL2FR2FLF";

    @BeforeEach
    void setUp() throws IOException {
        mazeTiny = new Maze("./examples/tiny.maz.txt");
        mazeSmall = new Maze("./examples/small.maz.txt");
    }

    @Test
    void shortestPathTiny(){
        graph = new GraphSolution(mazeTiny);
        Path path = graph.solve();
        Assertions.assertEquals(tinyShortestPath, path.getPath());
    }

    @Test
    void shortestPathSmall(){
        graph = new GraphSolution(mazeSmall);
        Path path = graph.solve();
        Assertions.assertEquals(smallShortestPath, path.getPath());
    }
}
