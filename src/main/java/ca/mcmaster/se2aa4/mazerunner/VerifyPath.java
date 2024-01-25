package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

import static ca.mcmaster.se2aa4.mazerunner.Player.Direction.*;

public class VerifyPath {

    public VerifyPath(){}

    private boolean verify(Maze maze, Path path, Player player, int[] start, int[] end){

        player.setLocation(maze, start);
        Movement movement = new Movement(player, maze, path);
        String raw_path = path.getPath();
        int len = raw_path.length();
        for(int i = 0; i < len; i++){

            if(raw_path.charAt(i) == 'F'){
                if(movement.canMove()){
                    movement.move();
                }
                else{
                    return false;
                }
            }
            else if(raw_path.charAt(i) == 'R'){
                movement.turnRight();
            }
            else if(raw_path.charAt(i) == 'L'){
                movement.turnLeft();
            }
        }

        return Arrays.equals(player.location(maze),end);
    }

    public boolean verify(Maze maze, Path path){
        path.toCanonicalForm();
        Player player = new Player(E);
        if(startWest(maze, path, player)){

            return true;
        }
        else{
            maze.clean();
            return startEast(maze, path, player);
        }
    }

    private boolean startWest(Maze maze, Path path, Player player){
        int[] start =  new int[] {maze.startingPosition(), 0};
        int[] end = new int[]{maze.endPosition(), maze.size() - 1};
        player.setDirection(E);

        return verify(maze, path, player, start, end);
    }

    private boolean startEast(Maze maze, Path path, Player player){
        int[] start = new int[]{maze.endPosition(), maze.size() - 1};
        int[] end =  new int[] {maze.startingPosition(), 0};
        player.setDirection(W);

        return verify(maze, path, player, start, end);
    }

}
