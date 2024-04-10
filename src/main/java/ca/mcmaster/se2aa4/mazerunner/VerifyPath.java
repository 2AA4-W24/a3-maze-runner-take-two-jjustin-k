package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class VerifyPath {

    private final Maze maze;

    private final Path path;

    private final Player player;

    public VerifyPath(Maze userMaze, Path userPath){
        maze = userMaze;
        path = userPath;
        player = new Player(maze);
    }

    private boolean verify(Point start, Point end){
        player.setLocation(start);
        String rawPath = path.getPath();
        int len = rawPath.length();
        for(int i = 0; i < len; i++){
            if(rawPath.charAt(i) == 'F'){
                if(player.canMove()){
                    player.move();
                }
                else{
                    return false;
                }
            }
            else if(rawPath.charAt(i) == 'R'){
                player.turnRight();
            }
            else if(rawPath.charAt(i) == 'L'){
                player.turnLeft();
            }
        }
        Point playerLoc = player.location();
        return Arrays.equals(playerLoc.getCoords(),end.getCoords());
    }

    public boolean verify(){
        if(maze.isEmpty()){
            return false;
        }
        else if (path.isFactored()){
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
