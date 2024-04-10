package ca.mcmaster.se2aa4.mazerunner;

public class BaseLine {

    private final ComputePath path;
    public BaseLine(ComputePath path1){
        path = path1;
    }

    public void speedUp(ComputePath path2){
        Path methodPath = speedOne(path2);
        methodPath.changeForm();
        String methodString = methodPath.getPath();
        Path baselinePath = baseSpeed();
        baselinePath.changeForm();
        String baselineString = baselinePath.getPath();
        float speedUp = (float) baselineString.length() / methodString.length();
        System.out.printf("SpeedUp = %.2f", speedUp);
        System.out.println();
    }

    private Path speedOne(ComputePath path2){
        double start1 = System.currentTimeMillis();
        Path methodPath = path2.solve();
        double finish1 = System.currentTimeMillis();
        System.out.println("Method execution time " + (finish1 - start1) + " ms");
        return methodPath;
    }

    private Path baseSpeed(){
        double start2 = System.currentTimeMillis();
        Path baselinePath = path.solve();
        double finish2 = System.currentTimeMillis();
        System.out.println("Baseline execution time " + (finish2 - start2)  + " ms");
        return baselinePath;
    }

}
