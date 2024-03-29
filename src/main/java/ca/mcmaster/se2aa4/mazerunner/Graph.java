package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public interface Graph {

    void addNode(Node node);

    void addEdge(Node n1, Node n2, int weight);

    void disp();

    List<Node> nodes();



}
