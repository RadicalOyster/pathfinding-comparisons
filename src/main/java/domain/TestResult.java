package domain;

public class TestResult {
    long dijkstraAverage;
    long aStarAverage;
    long idaAverage;
    
    public TestResult(long dijkstra, long aStar, long ida) {
        this.dijkstraAverage = dijkstra;
        this.aStarAverage = aStar;
        this.idaAverage = ida;
    }
    
    public long getDijkstraAverage() {
        return this.dijkstraAverage;
    }
    
    public long getAStarAverage() {
        return this.aStarAverage;
    }
    
    public long getIdaAverage() {
        return this.idaAverage;
    }
}
