package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {

    private static String maze = null;
    private final List<List<Character>> maze_map;
    public Maze(String maze) throws IOException {
        Maze.maze = maze;
        maze_map = map();
    }

    public boolean comparePaths(Path path) throws IOException {
        return path.getPath().equals(computePath().getPath());
    }

    public List<List<Character>> getMaze() {
        return maze_map;
    }

    private static Path computePath() throws IOException {
        ComputePath solution = new RightHand();
        return solution.solve(new Maze(maze));
    }

    private static List<List<Character>> map() throws IOException {
        List<List<Character>> mappings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("examples/straight.maz.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Character> row = getCharacters(line, mappings);
            mappings.add(row);
        }
        return mappings;
    }

    private static List<Character> getCharacters(String line, List<List<Character>> mappings) {
        List<Character> row = new ArrayList<>();
        for (int idx = 0; idx < line.length(); idx++) {
            if (line.charAt(idx) == '#') {
                row.add('#');
            }
            else if (line.charAt(idx) == ' ' || line.charAt(idx) == 0) {
                row.add(' ');
            }
        }
        if(Arrays.toString(new List[]{row}).equals("[[]]")){
            for(int i = 0; i < mappings.get(0).size(); i++){
                row.add(' ');
            }
        }
        return row;
    }

    public void display() throws IOException {
        for (List<Character> characters : maze_map) {
            for (Character character : characters) {
                if (character == '#') {
                    System.out.print('#');
                } else if (character == ' ') {
                    System.out.print(' ');
                } else {
                    System.out.print('p');
                }
            }
            System.out.println();
        }

    }

    public int startingPosition(){
        for(int i = 0; i < maze_map.size(); i++){
            if(maze_map.get(i).get(0) == ' '){
                maze_map.get(i).set(0, 'W');
                return i;
            }
        }
        return 0;
    }
    public int endPosition(){
        for(int i = 0; i < maze_map.size(); i++){
            if(maze_map.get(i).get(maze_map.size() -1) == ' '){
                return i;
            }
        }
        return 0;
    }
}
