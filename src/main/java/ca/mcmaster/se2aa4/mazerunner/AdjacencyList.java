package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList implements Graph{

    private final HashMap<Node, List<Edge>> listHashMap = new HashMap<>();

    private final List<Node> nodeList = new ArrayList<>();

    @Override
    public void addNode(Node node) {
        nodeList.add(node);
    }

    public void disp(){
        for(Node n : nodeList){
            System.out.println(Arrays.toString(n.point.getCoords()) + " ");
        }
        for(List<Edge> e : listHashMap.values()){
            for(Edge ed : e){
                System.out.println(Arrays.toString(ed.getNode1().getCoords()) + " " + ed.getWeight());
            }
        }
    }

    public List<Node> nodes(){
        return nodeList;
    }

    @Override
    public void addEdge(Node n1, Node n2, int weight) {
        Edge edge = new Edge(n2, weight);
        Edge edge2 = new Edge(n1, weight);
        if(listHashMap.get(n1) == null){
            List<Edge> edgeList1 = new ArrayList<>();
            edgeList1.add(edge);
            listHashMap.put(n1, edgeList1);
        }
        else{
            listHashMap.get(n1).add(edge);
        }

        if(listHashMap.get(n2) == null){
            List<Edge> edgeList2 = new ArrayList<>();
            edgeList2.add(edge2);
            listHashMap.put(n2, edgeList2);
        }
        else{
            listHashMap.get(n2).add(edge2);
        }
    }

    public List<Edge> edgeList(Node n){
        return listHashMap.get(n);
    }

}
