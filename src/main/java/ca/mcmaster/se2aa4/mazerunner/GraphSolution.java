package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class GraphSolution implements ComputePath{
    private Movement movement;

    private final Maze maze;

    private final Graph graph;

    private final Path path = new Path("");

    private final Node beg;

    private final Node end;


    public GraphSolution(Maze userMaze){
        maze = userMaze;
        GraphBuilder graphBuilder = new GraphBuilder(maze);
        graph = graphBuilder.build();
        beg = graph.nodes().get(0);
        end = graph.nodes().get(graph.nodes().size() -1);
    }

    @Override
    public Path solve() {
        return startPath();
    }

    private Path startPath(){
        Point coords = maze.eastCoords();
        player.setLocation(maze, coords);
        player.setDirection(Player.Direction.E);
        movement = new Movement(player, maze);
        findPath(nodesVisited());
        maze.clean();
        path.changeForm();
        return path;
    }

    private int[] dijkstra(){
        int[] path = new int[graph.nodes().size()];
        int[] cost = new int[graph.nodes().size()];
        Arrays.fill(cost, 1000);
        cost[beg.number()] = 0;
        Arrays.fill(path, 1000);
        path[beg.number()] = beg.number();
        Edge sn = new Edge(beg, 0);
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        queue.add(sn);
        while(!queue.isEmpty()){
            Node m = queue.poll().getNode();
            for(Edge edge : graph.edgeList(m)){
                Node n = edge.getNode();
                if(cost[m.number()] + edge.getWeight() < cost[n.number()]){
                    path[n.number()] = m.number();
                    cost[n.number()] = cost[m.number()] + edge.getWeight();
                    edge.setCost(cost[n.number()]);
                    queue.add(edge);
                }
            }
        }
        return path;
    }

    private List<Node> nodesVisited(){
        int[] paths = dijkstra();
        List<Node> pointList = new ArrayList<>();
        Node n = end;
        while(n != beg){
            pointList.add(n);
            n = graph.nodes().get(paths[n.number()]);
            player.setLocation(maze, n.getPoint());
        }
        pointList.add(n);
        return pointList;
    }

    private void findPath(List<Node> nodes){
        Node previous = nodes.get(nodes.size() - 1);
        for(int n = nodes.size() -2; n >= 0; n--){
            Point cur =  nodes.get(n).getPoint();
            Point prev = previous.getPoint();
            if(prev.getY() == cur.getY()){
                if(prev.getX() > cur.getX()){
                    towardsNode(Player.Direction.N);
                    int i = 0;
                    while(i < (prev.getX() - cur.getX())){
                        movement.move();
                        path.addToPath('F');
                        i ++;
                    }
                }
                else {
                    towardsNode(Player.Direction.S);
                    int i = 0;
                    while (i < (cur.getX() - prev.getX())) {
                        movement.move();
                        path.addToPath('F');
                        i++;
                    }
                }

            }
            else{
                if(prev.getY() > cur.getY()){
                    towardsNode(Player.Direction.W);
                    int i = 0;
                    while(i < (prev.getY() - cur.getY())){
                        movement.move();
                        path.addToPath('F');
                        i ++;
                    }
                }
                else {
                    towardsNode(Player.Direction.E);
                    int i = 0;
                    while (i < (cur.getY() - prev.getY())) {
                        movement.move();
                        path.addToPath('F');
                        i++;
                    }
                }
            }
           previous = nodes.get(n);
        }
    }

    private void towardsNode(Player.Direction nodeDirection){
        if(player.getDirection() == nodeDirection){
            return;
        }
        Player.Direction playerDirection;
        movement.turnRight();
        playerDirection = player.getDirection();
        if(playerDirection != nodeDirection){
            movement.turnLeft();
            while (playerDirection != nodeDirection) {
                movement.turnLeft();
                path.addToPath('L');
                playerDirection = player.getDirection();
            }
        }
        else{
            path.addToPath('R');
        }
    }

}
