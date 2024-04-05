package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class RightHand implements ComputePath {

    private Movement movement;

    private final Maze maze;

    private Point coords;

    public RightHand(Maze userMaze){
        maze = userMaze;
    }

    @Override
    public Path solve() {
        startPath();
        Point endLocation = new Point(coords.getY(), maze.size() -1);
        while(!Arrays.equals(player.location(maze).getCoords(), endLocation.getCoords())){
            if(rightWall() && movement.canMove()){
                movement.move();
                solution.addToPath('F');
            }
            else{
                tryOther();
            }
        }
        solution.changeForm();
        maze.clean();
        return solution;
    }

    private void startPath(){
        coords = maze.westCoords();
        player.setLocation(maze, coords);
        player.setDirection(Player.Direction.E);
        movement = new Movement(player, maze);
    }

    private void tryOther(){
        movement.turnRight();
        if(movement.canMove()){
            solution.addToPath('R');
        }
        else {
            movement.turnLeft();
            if(!movement.canMove()){

                while (!rightWall() || !movement.canMove()) {
                    movement.turnLeft();
                    solution.addToPath('L');
                }
            }

        }
        movement.move();
        solution.addToPath('F');
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
