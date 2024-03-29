package ca.mcmaster.se2aa4.mazerunner;

import java.util.Comparator;

public class Edge implements Comparator<Edge> {

    private final Node node1;

    private Integer weight = 0;

    private Integer cost = 0;

    public Edge(Node n1){
        node1 = n1;

    }

    public Edge(Node n1, Integer w){
        node1 = n1;
        weight = w;
    }

    public void setCost(Integer i){
        cost += i;
    }

    public Integer getCost(){
        return cost;
    }
    public Integer getWeight(){
        return weight;
    }

    public Point getNode1(){
        return node1.point;
    }

    public Node getNode(){
        return node1;
    }

    @Override
    public int compare(Edge edge, Edge t1) {
        return edge.getCost().compareTo(t1.getCost());
    }
}
