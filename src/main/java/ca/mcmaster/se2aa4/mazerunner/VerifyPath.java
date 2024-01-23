package ca.mcmaster.se2aa4.mazerunner;


import java.util.Arrays;

public class VerifyPath {

    public VerifyPath(){}

    public boolean verify(Maze maze, Path path){
        Player player = new Player();
        player.setLocation(maze, new int[] {maze.startingPosition(), 0});
        int[] end = new int[]{maze.endPosition(), maze.size() - 1};
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

        return Arrays.equals(move.player.location(maze),end);
    }

}
