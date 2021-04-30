package domain;

public class Timer {
    long startTime;
    long endTime;
    public long duration;
    
    public Timer() {
        this.startTime = 0;
        this.endTime = 0;
        this.duration = -1;
    }
    
    public void Start() {
        this.startTime = System.nanoTime();
    }
    
    public void Stop() {
        this.endTime = System.nanoTime();
        this.duration = (this.endTime - this.startTime);
    }
    
    public long getDuration() {
        return this.duration;
    }
}
