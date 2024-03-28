package ca.mcmaster.se2aa4.mazerunner;


public class Player {


    private Direction direction;
    public Player(){

    }

    public Point location(Maze maze){
        for(int i = 0; i < maze.getMaze().size(); i++){
            for(int j = 0; j < maze.getMaze().get(i).size(); j++){
                if(maze.getMaze().get(i).get(j) == 'p'){
                    return new Point (i, j);
                }
            }
        }
        return null;
    }

    public void setIcon(Maze maze, char ch){
        maze.getMaze().get(location(maze).getX()).set(location(maze).getY(), ch);
    }

    public void setLocation(Maze maze, Point end_point){
        maze.getMaze().get(end_point.getX()).set(end_point.getY(), 'p');
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void setDirection(Direction d){
        this.direction = d;
    }


    public enum Direction{
        N,
        W,
        E,
        S
    }
}
