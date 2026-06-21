package citybus;

public class Ticket {
    private String ticketId;
    private String busId;
    private double price;

    public Ticket(String ticketId, String busId, double price) {
        this.ticketId = ticketId;

        this.busId = busId;
        this.price = price;
    }

    @Override
    public String toString() {
        return ticketId + "," + busId + "," + price;
    }
}
