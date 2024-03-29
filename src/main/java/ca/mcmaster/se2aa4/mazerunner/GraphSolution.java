package ca.mcmaster.se2aa4.mazerunner;


public class GraphSolution implements ComputePath{

    private Movement movement;

    private final Maze maze;

    private Point coords;

    private final Graph graph = new AdjacencyList();

    public GraphSolution(Maze maze1){
        maze = maze1;
    }
    @Override
    public Path solve() {
        buildGraph();
        graph.disp();
        return null;
    }

    private void buildGraph(){
        for(int row = 1; row < maze.size() -1; row++){
            for(int col = 1; col < maze.colSize() -1; col++){
                int weight = 0;
                if(maze.pieceOnTile(row, col) == ' '){
                    if(inJunction(row, col)){
                        graph.addNode(new Node(maze.tile(row, col)));
                        weight = 0;
                    }
                    else{
                        weight++;
                    }
                }
            }
        }
    }

    private void findEdges(){

    }

    private boolean inJunction(int row, int col){
        int adj = 0;
        if(maze.pieceOnTile(row + 1, col) == ' '){
            adj++;
        }
        if( maze.pieceOnTile(row - 1, col) == ' ' ){
            adj++;
        }
        if(maze.pieceOnTile(row, col + 1) == ' '){
            adj++;
        }
        if(maze.pieceOnTile(row, col -1) == ' '){
            adj++;
        }
        return adj >=3;
    }
}
