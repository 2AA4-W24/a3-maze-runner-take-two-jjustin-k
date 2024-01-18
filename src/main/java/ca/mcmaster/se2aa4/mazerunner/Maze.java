package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    public Maze(String maze, String path){
    }

    public boolean comparePaths(String path){
        return path.equals(computePath());
    }

    private static String computePath(){
        return "no path yet";
    }
}
