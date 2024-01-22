package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public interface ComputePath {

    Path solve(Maze maze) throws IOException;
}
