package ca.mcmaster.se2aa4.mazerunner;



import org.apache.commons.cli.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            Configuration config = configure(args);
            logger.info("** Starting Maze Runner");
            logger.info("**** Reading the maze from file " + config.maze);
            Initializer initializer = new Initializer(config.maze, config.path, config.method);

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
        options.addOption("method", true, "Method of computing path");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd_line = parser.parse(options, args);

        return new Configuration(cmd_line.getOptionValue("i"), cmd_line.getOptionValue("p"), cmd_line.getOptionValue("method"));
    }

    private record Configuration(String maze, String path, String method){
        Configuration{
        }
    }


}
