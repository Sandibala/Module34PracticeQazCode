package week5.service;

public class HttpResult {
    private final boolean succes;
    private final long duration;

    public HttpResult(boolean succes, long duration){
        this.succes = succes;
        this.duration = duration;
    }
    public boolean isSuccess(){
        return succes;
    }
    public long getDuration(){
        return duration;
    }
}
