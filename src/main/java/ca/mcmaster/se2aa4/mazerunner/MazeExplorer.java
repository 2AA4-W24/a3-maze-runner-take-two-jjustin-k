package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class MazeExplorer {

    private final Maze maze;

    public MazeExplorer(String m) throws IOException {
        maze = new Maze(m);
    }

    public void solve(){
        ComputePath computation;
        GraphBuilder graphBuilder;
        Path solution;
        computation = new GraphSolution(maze);
        computation.solve();
    }

    public boolean verify(Path path){
        VerifyPath validity = new VerifyPath(maze, path);
        return validity.verify();
    }
}
