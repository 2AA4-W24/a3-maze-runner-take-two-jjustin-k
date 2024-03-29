package ca.mcmaster.se2aa4.mazerunner;

public class Edge {

    private final Node node1;

    private final Node node2;

    private int weight = 0;

    public Edge(Node n1, Node n2){
        node1 = n1;
        node2 = n2;
    }

    public Edge(Node n1, Node n2, int w){
        node1 = n1;
        node2 = n2;
        weight = w;
    }

    public int getWeight(){
        return weight;
    }

    public Point getNode1(){
        return node1.point;
    }

    public Point getNode2(){
        return node2.point;
    }

}
