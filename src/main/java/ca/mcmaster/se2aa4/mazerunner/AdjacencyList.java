package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList implements Graph{

    private final HashMap<Node, List<Edge>> listHashMap = new HashMap<>();

    private final List<Node> nodeList = new ArrayList<>();

    private final List<Edge> edgeList = new ArrayList<>();

    @Override
    public void addNode(Node node) {
        nodeList.add(node);
    }

    public void disp(){
        for(Node n : nodeList){
            System.out.println(Arrays.toString(n.point.getCoords()) + " ");
        }
    }

    public List<Node> nodes(){
        return nodeList;
    }
    @Override
    public void addEdge(Node n1, Node n2, String weight) {
        Edge edge = new Edge(n1, n2, weight);
        edgeList.add(edge);
    }


}
