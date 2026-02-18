package week5.model;

public class Result {
    private final int succesCount;
    private final int errorCount;
    private final double averageResponseTime;

    public Result(int succesCount, int errorCount, double averageResponseTime ){
        this.succesCount = succesCount;
        this.errorCount = errorCount;
        this.averageResponseTime = averageResponseTime;
    }

    public int getSuccessCount(){
        return  succesCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public  double getAverageResponseTime(){
        return averageResponseTime;
    }

    @Override
    public  String toString() {
        return "Result {" +
                " ucces = " + succesCount +
                " error = " + errorCount +
                " average = " + averageResponseTime + " }";
    }
}
