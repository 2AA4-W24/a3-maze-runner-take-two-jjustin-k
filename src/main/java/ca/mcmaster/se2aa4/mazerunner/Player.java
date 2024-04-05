package ca.mcmaster.se2aa4.mazerunner;

public class Player {

    private Direction direction;

    public Point location(Maze maze){
        for(int i = 0; i < maze.getMaze().size(); i++){
            for(int j = 0; j < maze.getMaze().get(i).size(); j++){
                if(maze.pieceOnTile(i, j) == 'p'){
                    return maze.tile(i,j);
                }
            }
        }
        return null;
    }

    public void setIcon(Maze maze, char ch){
        Point point = maze.tile(location(maze).getX(), location(maze).getY());
        point.setPiece(ch);
    }

    public void setLocation(Maze maze, Point endPoint){
        maze.tile(endPoint.getX(), endPoint.getY()).setPiece('p');
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
