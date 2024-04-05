package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class VerifyPath {

    private final Maze maze;

    private final Path path;

    private final Player player = new Player();

    public VerifyPath(Maze userMaze, Path userPath){
        maze = userMaze;
        path = userPath;
    }

    private boolean verify(Point start, Point end){
        player.setLocation(maze, start);
        Movement movement = new Movement(player, maze);
        String rawPath = path.getPath();
        int len = rawPath.length();
        for(int i = 0; i < len; i++){
            if(rawPath.charAt(i) == 'F'){
                if(movement.canMove()){
                    movement.move();
                }
                else{
                    return false;
                }
            }
            else if(rawPath.charAt(i) == 'R'){
                movement.turnRight();
            }
            else if(rawPath.charAt(i) == 'L'){
                movement.turnLeft();
            }
        }
        Point playerLoc = player.location(maze);
        return Arrays.equals(playerLoc.getCoords(),end.getCoords());
    }

    public boolean verify(){
        if (path.isFactored()){
            path.changeForm();
        }
        if(startWest()){
            return true;
        }
        else{
            maze.clean();
            return startEast();
        }
    }

    private boolean startWest(){
        Point start =  maze.westCoords();
        Point end = maze.eastCoords();
        player.setDirection(Player.Direction.E);
        return verify(start, end);
    }

    private boolean startEast(){
        Point start = maze.eastCoords();
        Point end =  maze.westCoords();
        player.setDirection(Player.Direction.W);
        return verify(start, end);
    }


}
