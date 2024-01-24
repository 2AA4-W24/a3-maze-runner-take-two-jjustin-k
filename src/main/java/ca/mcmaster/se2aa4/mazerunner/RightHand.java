package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import static ca.mcmaster.se2aa4.mazerunner.Player.Direction.E;


public class RightHand implements ComputePath {

    private Move move;
    public RightHand(){
    }

    @Override
    public Path solve(Maze maze) {
        Path solution = new Path("");
        Player p1 = new Player(E);
        p1.setLocation(maze, new int[] {maze.startingPosition(), 0});
        int[] end_location = {maze.endPosition(), maze.size() -1};
        move = new Move(p1, maze, solution);
        while(!Arrays.toString(p1.location(maze)).equals(Arrays.toString(end_location))){
            if(rightWall() && move.canMove()){
                move.move();
                solution.add_to_path();
            }
            else{
                tryOther(solution);
            }
        }
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
        solution.add_to_path();

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
