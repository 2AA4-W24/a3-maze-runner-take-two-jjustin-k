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
        String expanded = "";
        boolean has_num = false;
        for(int i = 0; i < path.length(); i++){
            if(Character.isDigit(path.charAt(i)) && path.charAt(i) != ' '){
                has_num = true;
                break;
            }
        }
        if(has_num){

            String number = "";
            for(int i = 0; i < path.length(); i++){
                if(Character.isDigit(path.charAt(i)) && path.charAt(i) != ' '){
                   number += path.charAt(i);
                }
                else{
                    if(!number.isEmpty()){
                        for(int j = 0; j < Integer.parseInt(number); j++){
                            if(path.charAt(i) == 'F'){
                                expanded += 'F';
                            }
                            else if(path.charAt(i) == 'R'){
                                expanded += 'R';
                            }
                            else if(path.charAt(i) == 'L'){
                                expanded += 'L';
                            }
                        }
                        number = "";
                    }
                    else{
                        if(path.charAt(i) == 'F'){
                            expanded += 'F';
                        }
                        else if(path.charAt(i) == 'R'){
                            expanded += 'R';
                        }
                        else if(path.charAt(i) == 'L'){
                            expanded += 'L';
                        }
                    }
                }
            }
            path = expanded;

        }
    }



}
