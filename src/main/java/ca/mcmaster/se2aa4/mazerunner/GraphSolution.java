package ca.mcmaster.se2aa4.mazerunner;

import java.util.*;

public class GraphSolution implements ComputePath{

    private final Maze maze;

    private final Graph graph;

    private final Path path = new Path();

    private final Player player;

    private final Node beg;

    private final List<Node> nodes;

    private final Node end;

    public GraphSolution(Maze userMaze){
        maze = userMaze;
        player = new Player(maze);
        GraphBuilder graphBuilder = new GraphBuilder(maze);
        graph = graphBuilder.build();
        nodes = graph.nodes();
        beg = nodes.get(0);
        end = nodes.get(nodes.size() -1);
    }

    @Override
    public Path solve() {
        return startPath();
    }

    private Path startPath(){
        if(maze.isEmpty()){
            return path;
        }
        Point coords = maze.eastCoords();
        player.setLocation(coords);
        player.setDirection(Player.Direction.E);
        findPath(nodesVisited());
        maze.clean();
        path.changeForm();
        return path;
    }

    private int[] dijkstra(){
        int[] path = new int[nodes.size()];
        int[] cost = new int[nodes.size()];
        Arrays.fill(cost, 10000);
        cost[beg.number()] = 0;
        Arrays.fill(path, 10000);
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
            n = nodes.get(paths[n.number()]);
            player.setLocation(n.getPoint());
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
                        player.move();
                        path.addToPath('F');
                        i ++;
                    }
                }
                else {
                    towardsNode(Player.Direction.S);
                    int i = 0;
                    while (i < (cur.getX() - prev.getX())) {
                        player.move();
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
                        player.move();
                        path.addToPath('F');
                        i ++;
                    }
                }
                else {
                    towardsNode(Player.Direction.E);
                    int i = 0;
                    while (i < (cur.getY() - prev.getY())) {
                        player.move();
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
        player.turnRight();
        playerDirection = player.getDirection();
        if(playerDirection != nodeDirection){
            player.turnLeft();
            while (playerDirection != nodeDirection) {
                player.turnLeft();
                path.addToPath('L');
                playerDirection = player.getDirection();
            }
        }
        else{
            path.addToPath('R');
        }
    }

}
