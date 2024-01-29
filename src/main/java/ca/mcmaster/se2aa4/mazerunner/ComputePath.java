package ca.mcmaster.se2aa4.mazerunner;

public interface ComputePath {

    Path solution = new Path("");
    Player player = new Player();

    Path solve();

}
