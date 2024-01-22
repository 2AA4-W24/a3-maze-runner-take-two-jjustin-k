package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Arrays;


public class RightHand implements ComputePath {

    private Move move;
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
        move = new Move(p1, maze, solution);
        //System.out.println(Arrays.toString(p1.location(maze)));

        int count = 0;
        while(!Arrays.toString(p1.location(maze)).equals(Arrays.toString(end_location))){
            while(rightWall() && move.canMove()){
                move.move();
            }
                tryOther(solution);
            count++;

        }
        System.out.println(solution.getPath());
        maze.display();
        System.out.println();
        System.out.println();
        return solution;
    }

    private void tryOther(Path solution){

        move.turnRight();

        if(move.canMove()){
            solution.add_to_path('R');
        }
        else {

            move.turnLeft();
            if(!move.canMove()){
                while (!rightWall() || !move.canMove()) {
                    move.turnRight();
                    solution.add_to_path('R');
                }
            }

        }
        move.move();



    }

    private boolean rightWall(){
        move.turnRight();
        if(move.canMove()){
            move.turnLeft();
            return false;

        }
        else {
            move.turnLeft();
            return true;
        }
    }

}
