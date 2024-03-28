package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class GraphSolution implements ComputePath{

    private Movement movement;

    private final Maze maze;

    private Point coords;

    private Graph graph = new AdjacencyList();

    public GraphSolution(Maze maze1){
        maze = maze1;
    }
    @Override
    public Path solve() {
        return null;
    }

    private int numberOfNodes(){
        int nodes = 0;
        for(List<Point> points : maze.getMaze()){
            for(Point p : points){
                if(p.getPiece() == ' ' || p.getPiece() == 'p'){
                    nodes++;
                }
            }
        }
        return nodes;
    }
}
