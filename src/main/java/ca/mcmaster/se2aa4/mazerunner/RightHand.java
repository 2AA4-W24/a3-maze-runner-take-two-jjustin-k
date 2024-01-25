package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import static ca.mcmaster.se2aa4.mazerunner.Player.Direction.E;


public class RightHand implements ComputePath {

    private Movement movement;
    public RightHand(){
    }

    @Override
    public Path solve(Maze maze) {
        player.setLocation(maze, new int[] {maze.startingPosition(), 0});
        int[] end_location = {maze.endPosition(), maze.size() -1};
        movement = new Movement(player, maze, solution);
        while(!Arrays.toString(player.location(maze)).equals(Arrays.toString(end_location))){
            if(rightWall() && movement.canMove()){
                movement.move();
                solution.add_to_path();
            }
            else{
                tryOther();
            }
        }
        solution.toFactoredForm();
        return solution;
    }

    private void tryOther(){

        movement.turnRight();

        if(movement.canMove()){

            solution.add_to_path('R');

        }
        else {
            movement.turnLeft();
            if(!movement.canMove()){
                while (!rightWall() || !movement.canMove()) {
                    movement.turnRight();

                    solution.add_to_path('R');

                }

            }

        }
        movement.move();
        solution.add_to_path();

    }

    private boolean rightWall(){
        movement.turnRight();
        if(movement.canMove()){
            movement.turnLeft();
            return false;

        }
        else {
            movement.turnLeft();
            return true;
        }
    }

}
