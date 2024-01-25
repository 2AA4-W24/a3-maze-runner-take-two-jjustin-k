package ca.mcmaster.se2aa4.mazerunner;

public class Path {

    private String path;
    public Path(String user_path){
        this.path = user_path;
    }

    public String getPath(){
        return path;
    }

    public void add_to_path(char ch){
        path += String.valueOf(ch);
    }

    public void toCanonicalForm(){
        StringBuilder expanded = new StringBuilder();
        if(isFactored()){
            StringBuilder number = new StringBuilder();
            for(int i = 0; i < path.length(); i++){
                if(Character.isDigit(path.charAt(i)) && path.charAt(i) != ' '){
                   number.append(path.charAt(i));
                }
                else{
                    if(!number.isEmpty()){
                        for(int j = 0; j < Integer.parseInt(number.toString()); j++){
                            if(path.charAt(i) == 'F'){
                                expanded.append('F');
                            }
                            else if(path.charAt(i) == 'R'){
                                expanded.append('R');
                            }
                            else if(path.charAt(i) == 'L'){
                                expanded.append('L');
                            }
                        }
                        number = new StringBuilder();
                    }
                    else{
                        if(path.charAt(i) == 'F'){
                            expanded.append('F');
                        }
                        else if(path.charAt(i) == 'R'){
                            expanded.append('R');
                        }
                        else if(path.charAt(i) == 'L'){
                            expanded.append('L');
                        }
                    }
                }
            }
            path = expanded.toString();
        }
    }

    public void toFactoredForm(){
        StringBuilder factored = new StringBuilder();
        if(!isFactored()){
            char current = 'z';
            int repeats = 0;
            for(int i = 0; i < path.length(); i++){
                if(i == 0){
                    current= path.charAt(i);
                }
                else{
                    if (path.charAt(i) != current) {
                        if(repeats > 1){
                            factored.append(repeats);
                        }
                        factored.append(current);
                        repeats = 0;
                        current = path.charAt(i);
                    }

                }
                repeats ++;
            }
            if(repeats > 1){
                factored.append(repeats);
            }
            factored.append(current);
            path = factored.toString();
        }
    }

    private boolean isFactored(){
        boolean has_num = false;
        for(int i = 0; i < path.length(); i++){
            if(Character.isDigit(path.charAt(i)) && path.charAt(i) != ' '){
                has_num = true;
                break;
            }
        }
        return has_num;
    }



}
