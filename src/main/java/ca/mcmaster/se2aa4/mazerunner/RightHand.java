package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import static ca.mcmaster.se2aa4.mazerunner.Player.Direction.E;


public class RightHand implements ComputePath {

    private Movement movement;

    public RightHand(){
    }

    @Override
    public Path solve(Maze maze) {
        int[] coords = maze.westEastCoords();
        player.setLocation(maze, new int[] {coords[0], 0});
        int[] end_location = {coords[1], maze.size() -1};
        movement = new Movement(player, maze, solution);
        while(!Arrays.toString(player.location(maze)).equals(Arrays.toString(end_location))){
            maze.display();
            if(rightWall() && movement.canMove()){
                movement.move();

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
                    movement.turnLeft();

                    solution.add_to_path('L');

                }
            }

        }
        movement.move();


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
