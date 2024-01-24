package ca.mcmaster.se2aa4.mazerunner;


import java.io.IOException;
import java.util.Arrays;

import static ca.mcmaster.se2aa4.mazerunner.Player.Direction.*;

public class VerifyPath {

    public VerifyPath(){}

    private boolean verify(Maze maze, Path path, Player player, int[] start, int[] end){

        player.setLocation(maze, start);
        Move move = new Move(player, maze, path);
        int len = path.getPath().length();
        for(int i = 0; i < len; i++){

            if(path.getPath().charAt(i) == 'F'){
                if(move.canMove()){
                    move.move();
                }
                else{
                    return false;
                }
            }
            else if(path.getPath().charAt(i) == 'R'){
                move.turnRight();
            }
            else if(path.getPath().charAt(i) == 'L'){
                move.turnLeft();
            }
        }

        return Arrays.equals(player.location(maze),end);
    }

    public boolean verify(Maze maze, Path path){
        Player player = new Player(E);
        if(startWest(maze, path, player)){
            player.resetLocation(maze);
            return true;
        }
        else{
            maze.clean();
            if(startEast(maze, path, player)){
                player.resetLocation(maze);
                return true;
            }
        }
        return false;
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
