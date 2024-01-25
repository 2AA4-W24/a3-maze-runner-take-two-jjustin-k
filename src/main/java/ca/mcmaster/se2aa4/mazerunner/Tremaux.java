package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Tremaux implements ComputePath{

    private Movement movement;

    private Player.Direction direction;

    private Maze maze;

    private final char single_mark = '~';

    private final char double_mark = 'x';

    public Tremaux(){}
    @Override
    public Path solve(Maze user_maze) {
        maze = user_maze;
        player.setLocation(maze, new int[] {maze.startingPosition(), 0});
        int[] end_location = {maze.endPosition(), maze.size() -1};
        movement = new Movement(player, maze, solution);
        int c = 0;
        while(!Arrays.toString(player.location(maze)).equals(Arrays.toString(end_location))){
            c++;
            if(Arrays.toString(player.location(maze)).equals(Arrays.toString(end_location))){break;}
            maze.display();
            System.out.println();
            if(movement.canMove() && !Arrays.toString(movement.nextStep()).equals(Arrays.toString(end_location))){

                if(junctionAhead()){
                    movement.move();
                    markEntrance(single_mark);
                    System.out.println("line 45");
                }
                else if(turnAhead()){
                    System.out.println(player.getDirection());
                    movement.move();
                   markEntrance(single_mark);
                    System.out.println("line 50");
                }
                else{
                    findOpening();
                    System.out.println("line 54");
                }

            }
            else{
                findOpening();System.out.println("line 59");

            }

        }
        solution.toFactoredForm();
        return solution;
    }


    private void findOpening(){
        direction = player.getDirection();
        if(nextChar(movement.nextStep()) == single_mark){
            while(nextChar(movement.nextStep()) == single_mark || nextChar(movement.nextStep()) == '#'){
                movement.turnRight(); movement.turnRight(); System.out.println("77");
                if(player.getDirection() == direction){
                    turnAround();
                    break;
                }
            }
            movement.move();
            markEntrance(single_mark);

        }
        else if(inJunction()){
            arbitraryUnmarked();
            movement.move();
            System.out.println("81");
        }
        else if(nextChar(movement.nextStep()) == single_mark){
            movement.move();
            markEntrance(double_mark);
            System.out.println("87");
        }
        else{
            while(!movement.canMove() || nextChar(movement.nextStep()) == single_mark){
                movement.turnRight();
                System.out.println("97");
                if(player.getDirection() == direction){
                    turnAround();
                    break;
                }
            }movement.move();
            markEntrance(single_mark);

        }

    }

    private Character nextChar(int[] tile){
       return maze.getMaze().get(tile[0]).get(tile[1]);
    }

    private Player.Direction oppositeDirection(){
        movement.turnRight();
        movement.turnRight();
        direction = player.getDirection();
        movement.turnLeft();
        movement.turnLeft();
        return direction;
    }
    private void mark(int[] location, char ch){
        maze.getMaze().get(location[0]).set(location[1], ch);
    }

    private void markEntrance(char ch){
        turnAround();
        if(movement.canMove()){
            mark(movement.nextStep(),ch);
        }
        turnAround();

    }


    private void turnAround(){
        movement.turnRight();
        movement.turnRight();
    }

    private void arbitraryUnmarked(){
        List<Player.Direction> directionList = new ArrayList<>();
        int[] tile = movement.nextStep();
        List<List<Character>> mapping = maze.getMaze();
        Character ch = mapping.get(tile[0]).get(tile[1]);
        if(movement.canMove()){
            if(ch ==' '){
                directionList.add(player.getDirection());
            }
        }
        movement.turnRight();
        tile = movement.nextStep();
        mapping = maze.getMaze();
        ch = mapping.get(tile[0]).get(tile[1]);
        if(movement.canMove()){
            if(ch ==' '){
                directionList.add(player.getDirection());
            }
        }
        movement.turnLeft();
        movement.turnLeft();
        tile = movement.nextStep();
        mapping = maze.getMaze();
        ch = mapping.get(tile[0]).get(tile[1]);
        if(movement.canMove()){
            if(ch ==' '){
                directionList.add(player.getDirection());
            }
        }
        movement.turnRight();

        if(directionList.isEmpty()){System.out.println(directionList);
            turnAround();
            movement.move();
            markEntrance(double_mark);


        }
        else{
            Collections.shuffle(directionList);
            player.setDirection(directionList.get(0));
            movement.move();
        }
    }




    private boolean turnAhead(){
        int [] next;
        movement.move();
        movement.turnRight();
        next = movement.nextStep();
        if(maze.getMaze().get(next[0]).get(next[1]) != '#'){
            movement.turnLeft();
            return true;
        }
        movement.turnLeft();
        movement.turnLeft();
        next = movement.nextStep();
        if(maze.getMaze().get(next[0]).get(next[1]) != '#'){
            movement.turnRight();
            return true;
        }
        movement.turnRight();
        turnAround();
        movement.move();
        turnAround();
        return false;
    }

    private boolean junctionAhead(){
        movement.move();
        boolean result = inJunction();
        turnAround();
        movement.move();
        turnAround();
        return result;
    }

    private boolean inJunction(){
        int[] location;
        int paths = 0;
        location = movement.nextStep();
        if(maze.getMaze().get(location[0]).get(location[1]) != '#'){
            paths++;
        }
        movement.turnRight();
        location = movement.nextStep();
        if(maze.getMaze().get(location[0]).get(location[1]) != '#'){
            paths++;
        }
        movement.turnLeft();
        movement.turnLeft();
        location = movement.nextStep();
        if(maze.getMaze().get(location[0]).get(location[1]) != '#'){
            paths++;
        }
        movement.turnRight();
        return paths >1;
    }

}
