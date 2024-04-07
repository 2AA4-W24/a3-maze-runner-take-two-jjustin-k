package ca.mcmaster.se2aa4.mazerunner;

public class Path {

    private String path = "";

    public Path(String userPath){
        this.path = userPath;
    }

    public Path(){}

    public String getPath(){
        return path;
    }

    public void addToPath(char ch){
        path += String.valueOf(ch);
    }

    private void toCanonicalForm(){
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

    private void toFactoredForm(){
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

    public void changeForm(){
        if(isFactored()){
            toCanonicalForm();
        }
        else {
            toFactoredForm();
        }
    }

    public boolean isFactored(){
        boolean hasNum = false;
        for(int i = 0; i < path.length(); i++){
            if(Character.isDigit(path.charAt(i)) && path.charAt(i) != ' '){
                hasNum = true;
                break;
            }
        }
        return hasNum;
    }




}
