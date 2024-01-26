package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    private static String maze = null;
    private final List<List<Character>> maze_map;
    public Maze(String maze) throws IOException {
        Maze.maze = maze;
        maze_map = map();
    }


    public List<List<Character>> getMaze() {
        return maze_map;
    }

    public void solve(Method method) throws IOException {
        ComputePath computation;
        Path solution;
        if(method == null){
            computation = new RightHand(new Maze(maze));
            solution = computation.solve();
            System.out.println(solution.getPath());
        }
        else {
            if(method.equals("righthand")){
                computation = new RightHand(new Maze(maze));
                solution = computation.solve();
                System.out.println(solution.getPath());
            }
            else if (method.equals("tremaux")){
                computation = new Tremaux(new Maze(maze));
                solution = computation.solve();
                System.out.println(solution.getPath());
            }
        }
    }

    private static List<List<Character>> map() throws IOException {
        List<List<Character>> mappings = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(Maze.maze));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Character> row = getCharacters(line, mappings);
            mappings.add(row);
        }
        for(List<Character> list : mappings){
            if(list.size() != mappings.get(0).size()){
                while(list.size() != mappings.get(0).size()){
                    list.add(' ');
                }
            }
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

        return row;
    }

    public void display(){
        //Used to keep track of position
        for (List<Character> characters : maze_map) {
            for (Character character : characters) {
                if (character == '#') {
                    System.out.print('#');
                } else if (character == ' ') {
                    System.out.print('_');
                } else {
                    System.out.print(character);
                }
            }
            System.out.println();
        }

    }

    private int westPosition(){
        for(int i = 0; i < maze_map.size(); i++){
            if(maze_map.get(i).get(0) == ' '){
                return i;
            }
        }
        return 0;
    }
    private int eastPosition(){
        for(int i = 0; i < maze_map.size(); i++){
            if(maze_map.get(i).get(maze_map.size() -1) == ' '){
                return i;
            }
        }
        return 0;
    }

    public int[] westEastCoords(){
        return new int[] {westPosition(), eastPosition()};
    }
    public int size(){
        return maze_map.size();
    }


}
