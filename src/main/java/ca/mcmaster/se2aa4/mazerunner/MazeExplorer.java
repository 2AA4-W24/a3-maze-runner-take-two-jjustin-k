package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class MazeExplorer {

    private final Maze maze;

    public MazeExplorer(String m) throws IOException {
        maze = new Maze(m);
    }

    public void solve() throws IOException {
        ComputePath computation;
        Path solution;
        computation = new RightHand(maze);
        solution = computation.solve();
        System.out.println(solution.getPath());
    }

    public boolean verify(Path path) throws IOException {
        VerifyPath validity = new VerifyPath(maze, path);
        return validity.verify();
    }
}
