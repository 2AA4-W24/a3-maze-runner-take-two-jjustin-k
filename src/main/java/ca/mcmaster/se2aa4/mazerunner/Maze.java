package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private static String MAZE = null;
    private final String PATH;
    public Maze(String maze, String path){
        MAZE = maze;
        PATH = path;
    }

    public boolean comparePaths(){
        return PATH.equals(computePath());
    }

    private static String computePath(){
        ComputePath solution = new RightHand();
        return solution.solve(MAZE);
    }
}
