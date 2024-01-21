package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RightHand implements ComputePath {

    public RightHand(){
    }

    @Override
    public Path solve(Maze maze) throws IOException {
        Path solution = new Path("");
        Player p1 = new Player();
        int start = maze.startingPosition();
        
        p1.setLocation(maze, new int[] {start, 0});
        int end = maze.endPosition();
        int[] end_location = {end, maze.getMaze().size() -1};
        maze.display();
        System.out.println();
        System.out.println();
        while(!Arrays.toString(p1.location(maze)).equals(Arrays.toString(end_location))){
            Move move = new Move(p1, maze, solution);
            move.move();
            maze.display();
            System.out.println();
            System.out.println();

        }
        return solution;

    }








}
