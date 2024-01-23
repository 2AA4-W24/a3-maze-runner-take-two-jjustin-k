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

    public void changeForm(){
        boolean has_num = false;
        for(int i = 0; i < path.length(); i++){
            if(path.charAt(i) != 'F' || path.charAt(i) != 'R' || path.charAt(i) != 'L'){
                has_num = true;
                break;
            }
        }
        if(has_num){
            String expanded = "";
            for(int i = 0; i < path.length(); i++){
                if(path.charAt(i) != 'F' || path.charAt(i) != 'R' || path.charAt(i) != 'L'){
                   switch (path.charAt(i)){
                       case '2' -> {
                           expanded += path.charAt(i+1) + String.valueOf(path.charAt(i+1)) ;
                       }
                       case '3' -> {
                           expanded += path.charAt(i+1) + String.valueOf(path.charAt(i+1)) + path.charAt(i+1);
                       }
                       case '4' -> {
                           for(int j = 0; j < 4; j++) {
                               expanded += String.valueOf(path.charAt(i+1));
                           }
                       }
                   }
                }
            }
            path = expanded;
        }
    }



}
