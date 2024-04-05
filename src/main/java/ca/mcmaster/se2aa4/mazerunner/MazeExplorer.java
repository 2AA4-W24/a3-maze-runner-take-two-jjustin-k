package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class MazeExplorer {

    private final Maze maze;

    public MazeExplorer(String m) throws IOException {
        maze = new Maze(m);
    }

    public void solve(String method){
        ComputePath computation;
        if("righthand".equals(method)){
            computation = new RightHand(maze);
        }
        else{
            computation = new GraphSolution(maze);
        }
        Path solution = computation.solve();
        System.out.println(solution.getPath());
    }

    public void compare(String baseline, String method){
        BaseLine base;
        if("righthand".equals(baseline)){
            base = new BaseLine(new RightHand(maze));
        }
        else{
            base = new BaseLine(new GraphSolution(maze));
        }
        System.out.println("Time to read maze from file: " + maze.readTime() + " ms");
        if("righthand".equals(method)){
            base.speedUp(new RightHand(maze));
        }
        else{
            base.speedUp(new GraphSolution(maze));
        }

    }

    public boolean verify(Path path){
        VerifyPath validity = new VerifyPath(maze, path);
        return validity.verify();
    }
}
