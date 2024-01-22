package ca.mcmaster.se2aa4.mazerunner;

public class Move {

    private final Player player;

    private final Maze maze;

    private final Path path;

    public Move(Player p1, Maze maze1, Path path1){
        player = p1;
        maze = maze1;
        path = path1;
    }

    private int[] nextStep(){
        switch(player.getDirection()){
            case N-> {
                return new int[] {player.location(maze)[0] - 1, player.location(maze)[1]};
            }
            case S -> {
                return new int[] {player.location(maze)[0] + 1, player.location(maze)[1]};
            }
            case W-> {
                return new int[] {player.location(maze)[0], player.location(maze)[1] - 1};
            }
            default -> {
                return new int[] {player.location(maze)[0], player.location(maze)[1] + 1};
            }
        }
    }

    private boolean canMove(int[] result){
        if(result[0] >= 0 && result[0] < maze.getMaze().size() && result[1] >= 0 && result[1] < maze.getMaze().size() ){
            return maze.getMaze().get(result[0]).get(result[1]) == ' ';
        }
        return false;
    }

    public void move(){
        int[] result = nextStep();
        if(canMove(result)){
           player.setIcon(maze,' ');
           player.setLocation(maze, result);
           path.add_to_path();
        }
    }

}
