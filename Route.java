package citybus;

public class Route {
    private String routeId;
    private String start;
    private String destination;

    public Route(String routeId, String start, String destination) {
        this.routeId = routeId;
        this.start = start;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return routeId + "," + start + "," + destination;
    }
}
