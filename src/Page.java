public class Page {
    private String url;
    private int visitNumber;
    private String visitTime;

    public Page(String url, int visitNumber, String visitTime) {
        this.url = url;
        this.visitNumber = visitNumber;
        this.visitTime = visitTime;
    }

    public String getUrl() {
        return url;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public String toString() {
        return url + " (visit " + visitNumber + ", " + visitTime + ")";
    }

}
