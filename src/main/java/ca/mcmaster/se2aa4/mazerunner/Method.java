package ca.mcmaster.se2aa4.mazerunner;

public class Method {

    private final String method;

    public Method(String method){
        this.method = method;
    }


    public boolean equals(String result) {
        if(this.method != null){
            return method.equals(result);
        }
        return false;
    }
}
