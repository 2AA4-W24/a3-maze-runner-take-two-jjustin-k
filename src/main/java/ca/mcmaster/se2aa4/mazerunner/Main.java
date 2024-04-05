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
            initialize(config.maze, config.path, config.method, config.baseline);

        } catch(Exception e) {
            logger.error(e);
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
        options.addOption("baseline", true, "Comparison");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = parser.parse(options, args);

        return new Configuration(cmdLine.getOptionValue("i"), cmdLine.getOptionValue("p"), cmdLine.getOptionValue("method"), cmdLine.getOptionValue("baseline"));
    }

    private record Configuration(String maze, String path, String method, String baseline){
        Configuration{
        }
    }

    private static void initialize(String userMaze, String userPath, String method, String baseline) throws IOException {
        MazeExplorer mazeExplorer = new MazeExplorer(userMaze);
        if(userPath != null){
            Path path = new Path(userPath);
            if(mazeExplorer.verify(path)){
                System.out.println("correct path");
            }
            else{
                System.out.println("incorrect path");
            }
        }
        else if(baseline != null){
            mazeExplorer.compare(baseline, method);
        }
        else{
            mazeExplorer.solve(Objects.requireNonNullElse(method, "graph"));

        }
    }

}
