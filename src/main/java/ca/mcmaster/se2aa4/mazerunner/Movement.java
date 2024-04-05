package ca.mcmaster.se2aa4.mazerunner;

public class Movement {

    private final Player player;

    private final Maze maze;

    public Movement(Player p1, Maze maze1) {
        player = p1;
        maze = maze1;
    }

    private Point nextStep() {
        switch (player.getDirection()) {
            case N -> {
                return new Point(player.location().getX() - 1, player.location().getY());
            }
            case S -> {
                return new Point(player.location().getX() + 1, player.location().getY());
            }
            case W -> {
                return new Point(player.location().getX(), player.location().getY() - 1);
            }
            default -> {
                return new Point(player.location().getX(), player.location().getY() + 1);
            }
        }
    }

    public boolean canMove() {
        Point result = nextStep();
        if (result.getX() >= 0 && result.getX() < maze.size() && result.getY() >= 0 && result.getY() < maze.getMaze().get(0).size()) {
            return maze.pieceOnTile(result.getX(), result.getY()) != '#';
        }
        return false;
    }

    public void move() {
        Point result = nextStep();
        if (canMove()) {
            player.setIcon(' ');
            player.setLocation(result);
        }
    }

    public void turnRight() {
        switch (player.getDirection()) {
            case E -> player.setDirection(Player.Direction.S);
            case N -> player.setDirection(Player.Direction.E);
            case W -> player.setDirection(Player.Direction.N);
            default -> player.setDirection(Player.Direction.W);
        }

    }

    public void turnLeft() {
        switch (player.getDirection()) {
            case E -> player.setDirection(Player.Direction.N);
            case N -> player.setDirection(Player.Direction.W);
            case W -> player.setDirection(Player.Direction.S);
            default -> player.setDirection(Player.Direction.E);
        }

    }
}

