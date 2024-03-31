package ca.mcmaster.se2aa4.mazerunner;

import java.lang.reflect.Array;
import java.util.*;

public class GraphSolution implements ComputePath{
    private Movement movement;

    private final Maze maze;

    private Point coords;

    private final GraphBuilder graphBuilder;

    private final Graph graph;

    Node beg;

    Node end;


    public GraphSolution(Maze user_maze){
        maze = user_maze;
        graphBuilder = new GraphBuilder(maze);
        graph = graphBuilder.build();
        beg = graph.nodes().get(0);
        end = graph.nodes().get(graph.nodes().size() -1);

    }

    @Override
    public Path solve() {
        startPath();
        return null;
    }

    private void startPath(){
        coords = maze.westEastCoords();
        player.setLocation(maze, new Point(coords.getX(), 0));
        player.setDirection(Player.Direction.E);
        movement = new Movement(player, maze);
        System.out.println(Arrays.toString(dijkstra()));
        printNodes();
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
                    //System.out.println(Arrays.toString(n.point.getCoords()));
                    cost[n.number()] = cost[m.number()] + edge.getWeight();
                    edge.setCost(cost[n.number()]);
                    queue.add(edge);
                }
            }
        }
       // System.out.println(Arrays.toString(cost));
        //maze.display();
        return path;
    }

    private void printNodes(){
        int[] paths = dijkstra();
        Node n = end;
        while(n != beg){
            //System.out.println(Arrays.toString(n.point.getCoords()));
            n = graph.nodes().get(paths[n.number()]);
            player.setLocation(maze, n.point);
            maze.display();
        }
    }

    private void printEdges(){
        for(Node n : graph.nodes()){
            System.out.print(Arrays.toString(n.point.getCoords()) + " edges : ");
            if(graph.edgeList(n) != null){
            for(Edge e : graph.edgeList(n)){
                System.out.print(Arrays.toString(e.getNode1().getCoords()) + " ");
            }

        } System.out.println();}
    }
}
