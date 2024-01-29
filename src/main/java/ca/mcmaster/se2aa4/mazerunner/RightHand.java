package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class RightHand implements ComputePath {

    private Movement movement;

    private final Maze maze;

    private int [] coords;

    public RightHand(Maze user_maze){
        maze = user_maze;
    }

    @Override
    public Path solve() {
        startPath();
        int[] end_location = {coords[1], maze.size() -1};
        while(!Arrays.equals(player.location(maze), end_location)){
            if(rightWall() && movement.canMove()){
                movement.move();
                solution.add_to_path('F');
            }
            else{
                tryOther();
            }
        }
        solution.changeForm();
        return solution;
    }

    private void startPath(){
        coords = maze.westEastCoords();
        player.setLocation(maze, new int[] {coords[0], 0});
        movement = new Movement(player, maze);
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
        solution.add_to_path('F');
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
