package ca.mcmaster.se2aa4.mazerunner;

public class Point {

    private final int x;

    private final int y;

    private Character piece = ' ';

    public Point(int x1, int y1){
        x = x1;
        y = y1;
    }

    public Point(int x1, int y1, char ch){
        x = x1;
        y = y1;
        piece = ch;
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

    public char getPiece(){
        return piece;
    }

    public void setPiece(char ch){
        piece = ch;
    }
}
