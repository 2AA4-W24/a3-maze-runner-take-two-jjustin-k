package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class RightHand implements ComputePath {

    private final Maze maze;

    private final Player player;

    private final Path solution = new Path();

    public RightHand(Maze userMaze){
        maze = userMaze;
        player = new Player(maze);
    }

    @Override
    public Path solve() {
        if(maze.isEmpty()){
            return solution;
        }
        startPath();
        Point endLocation = maze.eastCoords();
        while(!Arrays.equals(player.location().getCoords(), endLocation.getCoords())){
            if(rightWall() && player.canMove()){
                player.move();
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
        Point coords = maze.westCoords();
        player.setLocation(coords);
        player.setDirection(Player.Direction.E);
    }

    private void tryOther(){
        player.turnRight();
        if(player.canMove()){
            solution.addToPath('R');
        }
        else {
            player.turnLeft();
            if(!player.canMove()){
                while (!rightWall() || !player.canMove()) {
                    player.turnLeft();
                    solution.addToPath('L');
                }
            }
        }
        player.move();
        solution.addToPath('F');
    }

    private boolean rightWall(){
        player.turnRight();
        if(player.canMove()){
            player.turnLeft();
            return false;
        }
        else {
            player.turnLeft();
            return true;
        }
    }

}
