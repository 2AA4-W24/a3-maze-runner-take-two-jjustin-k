package ca.mcmaster.se2aa4.mazerunner;


import java.util.List;

public class GraphBuilder{

    private final Maze maze;

    private final Graph graph = new AdjacencyList();

    public GraphBuilder(Maze maze1){
        maze = maze1;
    }

    public Graph build() {
        buildGraph();
        graph.disp();
        maze.display();
        return graph;
    }

    private void buildGraph(){
        graph.addNode(new Node(maze.westCoords(),0));
        graph.addNode(new Node(maze.eastCoords(), 1));
        int nodeNumber = 2;
        for(int row = 1; row < maze.size() -1; row++){
            for(int col = 1; col < maze.colSize() -1; col++){
                if(maze.pieceOnTile(row, col) == ' '){
                    if(inJunction(row, col)){
                        graph.addNode(new Node(maze.tile(row, col), nodeNumber));
                        nodeNumber++;
                    }
                }
            }
        }
        findEdges();
    }

    private void findEdges(){
        List<Node> nodes = graph.nodes();
        for(Node n : graph.nodes()){
            for(int i = graph.nodes().indexOf(n) + 1; i < graph.nodes().size(); i++){
               Point p1 = n.point;
               Point p2 = nodes.get(i).point;
               if(clearPathX(p1, p2)){
                   graph.addEdge(n, nodes.get(i), distance(p1.getY(), p2.getY()));
                   graph.addEdge(nodes.get(i), n,distance(p1.getY(), p2.getY()));
               }
               else if(clearPathY(p1, p2)){
                   graph.addEdge(n, nodes.get(i), distance(p1.getX(), p2.getX()));
                   graph.addEdge(nodes.get(i), n, distance(p1.getX(), p2.getX()));
               }
            }
        }
    }

    private int distance(int x1, int x2){
        return Math.abs(x1 - x2);
    }

    private boolean clearPathX(Point p1, Point p2) {
        boolean notBlocked = true;
        if (p1.getX() == p2.getX()) {
            if (p1.getY() > p2.getY()) {
                for (int j = p2.getY() + 1; j < p1.getY(); j++) {
                    if (maze.pieceOnTile(p1.getX(), j) == '#') {
                        notBlocked = false;
                        break;
                    }
                }
            } else {
                for (int j = p1.getY() + 1; j < p2.getY(); j++) {
                    if (maze.pieceOnTile(p1.getX(), j) == '#') {
                        notBlocked = false;
                        break;
                    }
                }
            }
            return notBlocked;
        }
        return false;
    }

    private boolean clearPathY(Point p1, Point p2){
        boolean notBlocked = true;
        if(p1.getY() == p2.getY()){
            if(p1.getX() > p2.getX()){
                for(int j = p2.getX() + 1; j < p1.getX(); j++){
                    if(maze.pieceOnTile(j, p1.getY()) == '#'){
                        notBlocked = false;
                        break;
                    }
                }
            }
            else{
                for(int j = p1.getX() + 1; j < p2.getX(); j++){
                    if(maze.pieceOnTile(j, p1.getY()) == '#'){
                        notBlocked = false;
                        break;
                    }
                }
            }
            return notBlocked;
        }
        return false;
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
        return adj >=2;
    }
}
