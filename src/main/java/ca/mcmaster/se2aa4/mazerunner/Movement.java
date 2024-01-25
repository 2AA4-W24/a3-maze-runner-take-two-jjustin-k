package ca.mcmaster.se2aa4.mazerunner;


public class Movement {

    public Player player;

    private final Maze maze;

    private final Path path;


    public Movement(Player p1, Maze maze1, Path path1){
        player = p1;
        maze = maze1;
        path = path1;
    }

    public int[] nextStep(){
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

    public boolean canMove(){
       int[] result = nextStep();

        if(result[0] >= 0 && result[0] < maze.size() && result[1] >= 0 && result[1] < maze.size() ){
            if(maze.getMaze().get(result[0]).get(result[1]).toString().equals("#")) {
                return false;
            }
            return maze.getMaze().get(result[0]).get(result[1]) == ' ' || maze.getMaze().get(result[0]).get(result[1]) == '~';
        }
        return true;
    }

    public void move(){
        int[] result = nextStep();
        if(canMove()){
            player.setIcon(maze,' ');
            player.setLocation(maze, result);
            path.add_to_path('F');
        }

    }

    public void move(char ch){
        int[] result = nextStep();
        if(canMove()){
            player.setIcon(maze,ch);
            player.setLocation(maze, result);
            path.add_to_path('F');
        }
    }

    public void turnRight(){
        switch (player.getDirection()) {
            case E -> {
                player.setDirection(Player.Direction.S);

            }
            case N -> {
                player.setDirection(Player.Direction.E);

            }
            case W -> {
                player.setDirection(Player.Direction.N);

            }
            default -> {
                player.setDirection(Player.Direction.W);

            }
        }

    }

    public void turnLeft(){
        switch (player.getDirection()) {
            case E -> {
                player.setDirection(Player.Direction.N);

            }
            case N -> {
                player.setDirection(Player.Direction.W);

            }
            case W -> {
                player.setDirection(Player.Direction.S);

            }
            case S-> {
                player.setDirection(Player.Direction.E);

            }
        }

    }


}
