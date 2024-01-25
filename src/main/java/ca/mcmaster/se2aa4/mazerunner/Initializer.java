package ca.mcmaster.se2aa4.mazerunner;


import java.io.IOException;

public class Initializer {

    private final Maze maze;

    private final Path path;

    private final Method method;

    public Initializer(String user_maze, String user_path, String user_method) throws IOException {
        if(user_path != null){
            path = new Path(user_path);
        }
        else{
            path = null;
        }
        maze = new Maze(user_maze);
        method = new Method(user_method);
        initialize();
    }

    private void initialize() throws IOException {
        if(path != null){
            VerifyPath validity = new VerifyPath();
            if(validity.verify(maze, path)){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
        else{
            maze.solve(method);
        }
    }
}
