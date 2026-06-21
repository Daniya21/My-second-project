package citybus;

public class Driver {
    private String driverId;
    private String name;

    public Driver(String driverId, String name) {
        this.driverId = driverId;
        this.name = name;
    }

    @Override
    public String toString() {
        return driverId + "," + name;
    }
}
