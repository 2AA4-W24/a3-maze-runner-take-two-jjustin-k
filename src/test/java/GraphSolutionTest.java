import ca.mcmaster.se2aa4.mazerunner.GraphSolution;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Path;
import ca.mcmaster.se2aa4.mazerunner.VerifyPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GraphSolutionTest {

    private Maze mazeEmpty;

    private Maze mazeGiant;

    private Maze mazeHuge;

    private GraphSolution graphTiny;

    private GraphSolution graphSmall;

    private GraphSolution graphEmpty;

    private GraphSolution graphHuge;

    private GraphSolution graphGiant;

    @BeforeEach
    void setUp() throws IOException {
        Maze mazeTiny = new Maze("./examples/tiny.maz.txt");
        Maze mazeSmall = new Maze("./examples/small.maz.txt");
        mazeEmpty = new Maze("./examples/empty.maz.txt");
        mazeHuge = new Maze("./examples/huge.maz.txt");
        mazeGiant = new Maze("./examples/giant.maz.txt");
        graphTiny = new GraphSolution(mazeTiny);
        graphSmall = new GraphSolution(mazeSmall);
        graphEmpty = new GraphSolution(mazeEmpty);
        graphHuge = new GraphSolution(mazeHuge);
        graphGiant = new GraphSolution(mazeGiant);
    }

    @Test
    void shortestPathTiny(){
        Path path = graphTiny.solve();
        String tinyShortestPath = "3FL4FR3F";
        Assertions.assertEquals(tinyShortestPath, path.getPath());
    }

    @Test
    void shortestPathSmall(){
        Path path = graphSmall.solve();
        String smallShortestPath = "FLFR2FL6FR4FR2FL2FR2FLF";
        Assertions.assertEquals(smallShortestPath, path.getPath());
    }

    @Test
    void validSolutions(){
        VerifyPath verifyPath = new VerifyPath(mazeGiant, graphGiant.solve());
        Assertions.assertTrue(verifyPath.verify());
        verifyPath = new VerifyPath(mazeEmpty, graphEmpty.solve());
        Assertions.assertFalse(verifyPath.verify());
        verifyPath = new VerifyPath(mazeHuge, graphHuge.solve());
        Assertions.assertTrue(verifyPath.verify());

    }
}
