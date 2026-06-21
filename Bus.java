package citybus;

public class Bus {
    private String addbus;
    private String busid;
    private int capacity;
    private String driverId;
    private String routeId;

    public Bus(String busId, int capacity, String driverId, String routeId) {
        this.addbus = addbus;
        this.busid = busId;
        this.capacity = capacity;
        this.driverId = driverId;
        this.routeId = routeId;

    }

    public String getaddbus() { return addbus; }  //I used getter here
    public String getBusId() { return busid; }
    public int getCapacity() { return capacity; }
    public String getDriverId() { return driverId; }
    public String getRouteId() { return routeId; }


    @Override
    public String toString() {
        return addbus + "," + busid + "," + capacity + "," + driverId + "," + routeId;
    }
}
