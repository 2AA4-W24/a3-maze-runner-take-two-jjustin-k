package ca.mcmaster.se2aa4.mazerunner;

public class Node {

    private final Point point;

    private final int number;

    public Node(Point p, int num){
        point = p;
        number = num;
    }

    public int number(){
        return number;
    }

    public Point getPoint(){
        return point;
    }

}
