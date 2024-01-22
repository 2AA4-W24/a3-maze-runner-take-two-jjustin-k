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
        p1.setLocation(maze, new int[] {maze.startingPosition(), 0});
        int[] end_location = {maze.endPosition(), maze.getMaze().size() -1};
        move = new Move(p1, maze, solution);
        while(!Arrays.toString(p1.location(maze)).equals(Arrays.toString(end_location))){
            if(rightWall() && move.canMove()){
                move.move();
            }
            else{
                tryOther(solution);
            }
        }
        maze.display();
        System.out.println();
        System.out.println(solution.getPath());

        return solution;
    }

    private void tryOther(Path solution){

        move.turnRight();

        if(move.canMove()){
            solution.add_to_path(' ');
            solution.add_to_path('R');
            solution.add_to_path(' ');
        }
        else {

            move.turnLeft();
            if(!move.canMove()){
                while (!rightWall() || !move.canMove()) {
                    move.turnRight();
                    solution.add_to_path(' ');
                    solution.add_to_path('R');
                    solution.add_to_path(' ');
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
