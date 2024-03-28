package ca.mcmaster.se2aa4.mazerunner;

public class Edge {

    private final Node node1;

    private final Node node2;

    private char weight = 'F';

    public Edge(Node n1, Node n2){
        node1 = n1;
        node2 = n2;
    }

    public Edge(Node n1, Node n2, char w){
        node1 = n1;
        node2 = n2;
        weight = w;
    }

}
