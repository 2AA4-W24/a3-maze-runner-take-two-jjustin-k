package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {

    private final String maze;

    private final List<List<Point>> maze_map;

    public Maze(String maze1) throws IOException {
        maze = maze1;
        maze_map = map();
        display();
    }

    public List<List<Point>> getMaze() {
        return maze_map;
    }

    private List<List<Point>> map() throws IOException {
        List<List<Point>> mappings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(maze));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            List<Point> row = getCharacters(line, i);
            mappings.add(row);
            i++;
        }
        i =0;
        for(List<Point> list : mappings){
            if(list.size() != mappings.get(0).size()){
                int j = list.size();
                while(list.size() != mappings.get(0).size()){
                    list.add(new Point(i, j, ' '));
                    j++;
                }
            }
            i++;
        }
        return mappings;
    }

    private List<Point> getCharacters(String line, int i) {
        List<Point> row = new ArrayList<>();
        for (int idx = 0; idx < line.length(); idx++) {
            if (line.charAt(idx) == '#') {
                row.add(new Point(i, idx, '#'));
            }
            else if (line.charAt(idx) == ' ' || line.charAt(idx) == 0) {
                row.add(new Point(i, idx,' '));
            }
        }
        return row;
    }

    public void display(){
        for (List<Point> p : maze_map) {
            for (Point point : p) {
                //System.out.print(Arrays.toString(point.getCoords()) + " ");

                if (point.getPiece() == '#') {
                    System.out.print('#');
                } else if (point.getPiece() == ' ') {
                    System.out.print('_');
                } else {
                    System.out.print(point.getPiece());
                }


            }
            System.out.println();
        }
    }

    public char pieceOnTile(int row, int col){
        return tile(row,col).getPiece();
    }

    public Point tile(int row, int col){
        return maze_map.get(row).get(col);
    }

    private int westPosition(){
        for(int i = 0; i < maze_map.size(); i++){
            if(pieceOnTile(i,0) == ' '){
                return i;
            }
        }
        return 0;
    }
    private int eastPosition(){
        for(int i = 0; i < maze_map.size(); i++){
            if(pieceOnTile(i,maze_map.size() -1) == ' '){
                return i;
            }
        }
        return 0;
    }

    public Point westEastCoords(){
        return new Point (westPosition(), eastPosition());
    }

    public Point westCoords(){
        return new Point(westPosition(), 0);
    }

    public Point eastCoords(){
        return new Point(eastPosition(), size() -1);
    }

    public int size(){
        return maze_map.size();
    }

    public int colSize(){
        return maze_map.get(0).size();
    }


}
