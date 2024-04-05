package ca.mcmaster.se2aa4.mazerunner;

public class Player {

    private Direction direction;

    private final Movement movement;

    private final Maze maze;

    public Player(Maze maze1){
        maze = maze1;
        movement = new Movement(this, maze);
    }

    public Point location(){
        for(int i = 0; i < maze.getMaze().size(); i++){
            for(int j = 0; j < maze.getMaze().get(i).size(); j++){
                if(maze.pieceOnTile(i, j) == 'p'){
                    return maze.tile(i,j);
                }
            }
        }
        return null;
    }

    public void setIcon(char ch){
        Point point = maze.tile(location().getX(), location().getY());
        point.setPiece(ch);
    }

    public void setLocation(Point endPoint){
        maze.tile(endPoint.getX(), endPoint.getY()).setPiece('p');
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void setDirection(Direction d){
        this.direction = d;
    }

    public void move(){
        movement.move();
    }

    public void turnRight(){
        movement.turnRight();
    }

    public void turnLeft() {
        movement.turnLeft();
    }

    public boolean canMove(){
        return movement.canMove();
    }

    public enum Direction{
        N,
        W,
        E,
        S
    }

}
