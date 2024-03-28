package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;
import java.util.List;

import static ca.mcmaster.se2aa4.mazerunner.Player.Direction.*;

public class VerifyPath {

    private Point coords;

    private final Maze maze;

    private final Path path;

    private final Player player = new Player();

    public VerifyPath(Maze user_maze, Path user_path){
        maze = user_maze;
        path = user_path;
    }

    private boolean verify(Point start, Point end){
        player.setLocation(maze, start);
        System.out.println(Arrays.toString(player.location(maze).getCoords()));
        Movement movement = new Movement(player, maze);
        String raw_path = path.getPath();
        int len = raw_path.length();
        for(int i = 0; i < len; i++){
            maze.display();
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

        return Arrays.equals(player.location(maze).getCoords(),end.getCoords());
    }

    public boolean verify(){
        coords = maze.westEastCoords();
        if (path.isFactored()){
            path.changeForm();
        }
        if(startWest()){
            return true;
        }
        else{
            clean();
            return startEast();
        }
    }

    private boolean startWest(){
        Point start =  new Point (coords.getX(), 0);
        Point end = new Point(coords.getY(), maze.size() - 1);
        player.setDirection(E);

        return verify(start, end);
    }

    private boolean startEast(){
        Point start = new Point (coords.getY(), maze.size() - 1);
        Point end =  new Point (coords.getX(), 0);
        player.setDirection(W);

        return verify(start, end);
    }

    private void clean(){
        for(List<Character> list : maze.getMaze()){
            for(Character ch : list){
                if(ch == 'p'){
                    int idx = list.indexOf('p');
                    list.set(idx, ' ');
                }
            }
        }
    }

}
