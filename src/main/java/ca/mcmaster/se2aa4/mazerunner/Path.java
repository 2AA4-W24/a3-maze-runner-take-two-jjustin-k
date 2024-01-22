package ca.mcmaster.se2aa4.mazerunner;

public class Path {

    private String path;
    public Path(String user_path){
        path = user_path;
    }

    public String getPath(){
        return path;
    }

    public void add_to_path(){
        path += "F";
    }

    public void add_to_path(char ch){
        path += String.valueOf(ch);
    }

}
