package ca.mcmaster.se2aa4.mazerunner;

public class Point {

    private final int x;

    private final int y;

    public Point(int x1, int y1){
        x = x1;
        y = y1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getCoords(){
        return new int[] {x, y};
    }
}
