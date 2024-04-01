package ca.mcmaster.se2aa4.mazerunner;


import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Configuration config = configure(args);
            logger.info("** Starting Maze Runner");
            logger.info("**** Reading the maze from file " + config.maze);
            initialize(config.maze, config.path, config.method);

        } catch(Exception e) {
            logger.error(e);
            System.out.println(e);
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException{
        Options options = new Options();
        options.addOption("i", true, "Maze flag");
        options.addOption("p", true, "Path");
        options.addOption("method", true, "Exploration Method");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd_line = parser.parse(options, args);

        return new Configuration(cmd_line.getOptionValue("i"), cmd_line.getOptionValue("p"), cmd_line.getOptionValue("method"));
    }

    private record Configuration(String maze, String path, String method){
        Configuration{
        }
    }

    private static void initialize(String user_maze, String user_path, String method) throws IOException {
        MazeExplorer mazeExplorer = new MazeExplorer(user_maze);
        if(user_path != null){
            Path path = new Path(user_path);
            if(mazeExplorer.verify(path)){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
        else{
            mazeExplorer.solve(Objects.requireNonNullElse(method, "graph"));

        }
    }

}
