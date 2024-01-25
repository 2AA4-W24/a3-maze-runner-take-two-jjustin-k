package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Tremaux implements ComputePath{

    private Movement movement;

    private Player.Direction direction;

    private final Maze maze;

    private final char single_mark = '~';

    private final char double_mark = 'x';

    private final List<Character> decision_tracker = new ArrayList<Character>();
    public Tremaux(Maze user_maze){
        maze = user_maze;
    }


    @Override
    public Path solve() {

        int[] coords = maze.westEastCoords();
        player.setLocation(maze, new int[] {coords[0], 0});
        int[] end_location = {coords[1], maze.size() -1};
        movement = new Movement(player, maze, solution);
        int c = 0;
        while(!Arrays.toString(player.location(maze)).equals(Arrays.toString(end_location)) ){
            c++;
            if(Arrays.toString(player.location(maze)).equals(Arrays.toString(end_location))){break;}
            System.out.println();
            if(movement.canMove()){
                if(inJunction()){
                    decision_tracker.add('d');
                    arbitraryUnmarked();
                    movement.move();
                    maze.display();

                }
                else if(junctionAhead()){
                    decision_tracker.add(' ');
                    movement.move(single_mark);
                    System.out.println("line 45");
                    maze.display();

                }
                else if(turnAhead()){
                    System.out.println(player.getDirection());
                    movement.move(single_mark);
                    System.out.println("line 50");
                    decision_tracker.add(' ');
                    maze.display();
                }
                else{System.out.println("line 54");
                    if(nextChar(movement.nextStep()) != single_mark){
                        turnAround();
                        if(nextChar(movement.nextStep()) != single_mark){
                            turnAround();
                            movement.move();
                        }
                        else{
                            turnAround();
                            movement.move(single_mark);
                        }
                        maze.display();
                    }
                    else {
                        findOpening();System.out.println("line 54");
                    }


                }

            }
            else{
                findOpening();
                System.out.println("line 59");

            }

        }
        solution.changeForm();
        return solution;
    }


    private boolean mustMark(){
        if(!decision_tracker.isEmpty()){
            return decision_tracker.get(decision_tracker.size() - 2) == 'd';
        }
        return false;
    }
    private void findOpening(){
        movement.turnRight();
        if(movement.canMove() && nextChar(movement.nextStep()) != single_mark){
            solution.add_to_path('R');
            decision_tracker.add('d');
            movement.move();
            maze.display();


            return;
        }
            movement.turnLeft();
            movement.turnLeft();
        if(movement.canMove() && nextChar(movement.nextStep()) != single_mark){
            solution.add_to_path('L');
            decision_tracker.add('d');
            movement.move();
            maze.display();
            return;
        }
        movement.turnLeft();
        decision_tracker.add(' ');
        movement.move(double_mark);
        maze.display();

       
    }

    private Character nextChar(int[] tile){
       return maze.getMaze().get(tile[0]).get(tile[1]);
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
        if(maze.getMaze().get(next[0]).get(next[1]) == ' '){
            movement.turnLeft();
            return true;
        }
        movement.turnLeft();
        movement.turnLeft();
        next = movement.nextStep();
        if(maze.getMaze().get(next[0]).get(next[1]) == ' '){
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
        if(maze.getMaze().get(location[0]).get(location[1]) == ' '){
            paths++;
        }
        movement.turnRight();
        location = movement.nextStep();
        if(maze.getMaze().get(location[0]).get(location[1]) == ' '){
            paths++;
        }
        movement.turnLeft();
        movement.turnLeft();
        location = movement.nextStep();
        if(maze.getMaze().get(location[0]).get(location[1]) == ' '){
            paths++;
        }
        movement.turnRight();
        return paths >1;
    }

}
