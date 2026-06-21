package citybus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // -------------------- INPUT VALIDATION METHOD -------------------
    // This method ensures the user enters a valid integer
    // It keeps asking until a correct number is entered

    public static int getValidInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine(); // consume newline
                return value;
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.next(); // discard wrong input
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!Login.loginPage()) {
            return;
        }

        boolean mainMenuRunning = true;

        while (mainMenuRunning) { // Main menu loop
            System.out.println("\n--- CITY BUS MANAGEMENT SYSTEM ---");
            System.out.println("1. Manage Buses");
            System.out.println("2. Manage Drivers");
            System.out.println("3. Manage Passengers");
            System.out.println("4. Ticketing");
            System.out.println("5. Manage Routes");
            System.out.println("6. Exit");

            int choice = getValidInt(sc, "Choose option: ");

            switch (choice) {
                // ------------------- BUS MENU -------------------
                case 1:
                    boolean busMenuRunning = true;
                    // Bus menu loop
                    while (busMenuRunning) {
                        System.out.println("\n----BUS MANAGEMENT----");
                        System.out.println("1. Add Bus");
                        System.out.println("2. Remove Bus");
                        System.out.println("3. Assign Passenger to Bus");
                        System.out.println("4. Assign Route to Bus");
                        System.out.println("5. View Bus Occupancy & Available Seats");
                        System.out.println("6. Return to Main Menu");
                        System.out.println("7. Exit Program");

                        int busChoice = getValidInt(sc, "Choose option: ");

                        switch (busChoice) {
                            case 1: // Add Bus
                                System.out.print("Enter Bus ID: ");
                                String busId = sc.nextLine();
                                System.out.print("Enter Bus Capacity: ");
                                int capacity = getValidInt(sc, "");
                                System.out.print("Enter Driver ID: ");
                                String driverId = sc.nextLine();

                                FileManager.saveToFile(
                                        "busses.txt",
                                        busId + "," + capacity + "," + driverId + ","
                                );
                                System.out.println("Bus Added Successfully!");
                                break;

                            case 2: // Remove Bus
                                System.out.print("Enter Bus ID to remove: ");
                                String removeBusId = sc.nextLine();
                                List<String> busses = FileManager.loadFromFile("busses.txt");
                                List<String> updatedBusses = new ArrayList<>();
                                boolean found = false;

                                for (String bus : busses) {
                                    String[] parts = bus.split(",");
                                    if (parts[0].equalsIgnoreCase(removeBusId)) {
                                        found = true;
                                    } else {
                                        updatedBusses.add(bus);
                                    }
                                }

                                if (found) {
                                    FileManager.overwriteFile("busses.txt", updatedBusses);
                                    System.out.println("Bus removed successfully!");
                                } else {
                                    System.out.println("Bus not found!");
                                }
                                break;

                            case 3: // Assign passenger
                                System.out.print("Enter Bus ID: ");
                                String busForPassenger = sc.nextLine();
                                System.out.print("Enter Passenger ID: ");
                                String passengerId = sc.nextLine();
                                FileManager.saveToFile(
                                        "bus_passengers.txt",
                                        busForPassenger + "," + passengerId
                                );
                                System.out.println("Passenger " + passengerId + " assigned to Bus " + busForPassenger);
                                break;

                            case 4: // Assign route
                                System.out.print("Enter Bus ID: ");
                                String busForRoute = sc.nextLine();
                                System.out.print("Enter Route ID: ");
                                String routeId = sc.nextLine();
                                FileManager.saveToFile(
                                        "bus_routes.txt",
                                        busForRoute + "," + routeId
                                );
                                System.out.println("Route " + routeId + " assigned to Bus " + busForRoute);
                                break;

                            case 5: // View occupancy
                                List<String> allBusses = FileManager.loadFromFile("busses.txt");
                                if (allBusses.isEmpty()) {
                                    System.out.println("No buses available.");
                                } else {
                                    System.out.println("\nBusID | Capacity | DriverID | Occupied Seats");
                                    for (String b : allBusses) {
                                        String[] parts = b.split(",");
                                        String busID = parts[0];
                                        int cap = Integer.parseInt(parts[1]);
                                        String driver = parts[2];

                                        // Count passengers assigned to this bus
                                        List<String> busPassengers = FileManager.loadFromFile("bus_passengers.txt");
                                        int occupied = 0;
                                        for (String bp : busPassengers) {
                                            String[] bpParts = bp.split(",");
                                            if (bpParts[0].equalsIgnoreCase(busID)) {
                                                occupied++;
                                            }
                                        }
                                        System.out.println(busID + " | " + cap + " | " + driver + " | " + occupied);
                                    }
                                }
                                break;

                            case 6: // Return to main menu
                                busMenuRunning = false;
                                break;

                            case 7: // Exit program
                                System.out.println("Exiting program...");
                                busMenuRunning = false;
                                mainMenuRunning = false;
                                break;

                            default:
                                System.out.println("Invalid choice! Please choose between 1 and 7.");
                        }
                    }
                    break;

                // ------------------- DRIVER MENU -------------------
                case 2:
                    boolean driverMenuRunning = true;
                    while (driverMenuRunning) {
                        System.out.println("\n----DRIVER MANAGEMENT----");
                        System.out.println("1. Add new driver");
                        System.out.println("2. Remove Driver");
                        System.out.println("3. Assign Driver to Bus");
                        System.out.println("4. View Driver Details");
                        System.out.println("5. Return to Main Menu");
                        System.out.println("6. Exit Program");

                        int driverChoice = getValidInt(sc, "Choose option: ");
                        switch (driverChoice) {
                            case 1:
                                System.out.print("Enter Driver ID: ");
                                String driverID = sc.nextLine();
                                System.out.print("Enter Driver Name: ");
                                String driverName = sc.nextLine();
                                System.out.print("Enter Licence Number: ");
                                String licence = sc.nextLine();

                                FileManager.saveToFile(
                                        "drivers.txt",
                                        driverID + "," + driverName + "," + licence + ",NONE"
                                );
                                System.out.println("Driver added successfully!");
                                break;

                            case 2:
                                System.out.print("Enter Driver ID to remove: ");
                                String removeDriverID = sc.nextLine();
                                List<String> drivers = FileManager.loadFromFile("drivers.txt");
                                List<String> updatedDrivers = new ArrayList<>();
                                boolean driverFound = false;
                                for (String d : drivers) {
                                    String[] parts = d.split(",");
                                    if (parts[0].equalsIgnoreCase(removeDriverID)) {
                                        driverFound = true;
                                    } else {
                                        updatedDrivers.add(d);
                                    }
                                }
                                if (driverFound) {
                                    FileManager.overwriteFile("drivers.txt", updatedDrivers);
                                    System.out.println("Driver removed successfully!");
                                } else {
                                    System.out.println("Driver not found!");
                                }
                                break;

                            case 3:
                                System.out.print("Enter Driver ID: ");
                                String assignDriverId = sc.nextLine();
                                System.out.print("Enter Bus ID: ");
                                String assignBusId = sc.nextLine();

                                List<String> driverList = FileManager.loadFromFile("drivers.txt");
                                List<String> updatedDriverList = new ArrayList<>();
                                boolean assigned = false;

                                for (String d : driverList) {
                                    String[] parts = d.split(",");
                                    if (parts[0].equalsIgnoreCase(assignDriverId)) {
                                        updatedDriverList.add(parts[0] + "," + parts[1] + "," + parts[2] + "," + assignBusId);
                                        assigned = true;
                                    } else {
                                        updatedDriverList.add(d);
                                    }
                                }
                                if (assigned) {
                                    FileManager.overwriteFile("drivers.txt", updatedDriverList);
                                    System.out.println("Driver assigned to bus successfully!");
                                } else {
                                    System.out.println("Driver not found!");
                                }
                                break;

                            case 4:
                                List<String> allDrivers = FileManager.loadFromFile("drivers.txt");
                                if (allDrivers.isEmpty()) {
                                    System.out.println("No drivers available.");
                                } else {
                                    System.out.println("\nDriverID | Name | Licence | Bus");
                                    for (String d : allDrivers) {
                                        System.out.println(d);
                                    }
                                }
                                break;

                            case 5:
                                driverMenuRunning = false;
                                break;

                            case 6:
                                System.out.println("Exiting program...");
                                driverMenuRunning = false;
                                mainMenuRunning = false;
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                // ------------------- PASSENGER MENU -------------------
                case 3:
                    boolean passengerMenuRunning = true;
                    while (passengerMenuRunning) {
                        System.out.println("\n----PASSENGER MANAGEMENT----");
                        System.out.println("1. Add new Passenger");
                        System.out.println("2. Remove Passenger");
                        System.out.println("3. View Tickets booked");
                        System.out.println("4. View passenger history");
                        System.out.println("5. Return to Main Menu");
                        System.out.println("6. Exit Program");

                        int passengerChoice = getValidInt(sc, "Choose option: ");
                        switch (passengerChoice) {
                            case 1:
                                System.out.print("Enter Passenger ID: ");
                                String passengerId = sc.nextLine();
                                System.out.print("Enter Passenger Name: ");
                                String passengerName = sc.nextLine();
                                System.out.print("Enter Phone Number: ");
                                String phone = sc.nextLine();

                                FileManager.saveToFile(
                                        "passengers.txt",
                                        passengerId + "," + passengerName + "," + phone
                                );
                                System.out.println("Passenger added successfully!");
                                break;

                            case 2:
                                System.out.print("Enter Passenger ID to remove: ");
                                String removePassengerId = sc.nextLine();
                                List<String> passengers = FileManager.loadFromFile("passengers.txt");
                                List<String> updatedPassengers = new ArrayList<>();
                                boolean foundPassenger = false;
                                for (String p : passengers) {
                                    String[] parts = p.split(",");
                                    if (parts[0].equalsIgnoreCase(removePassengerId)) {
                                        foundPassenger = true;
                                    } else {
                                        updatedPassengers.add(p);
                                    }
                                }
                                if (foundPassenger) {
                                    FileManager.overwriteFile("passengers.txt", updatedPassengers);
                                    System.out.println("Passenger removed successfully!");
                                } else {
                                    System.out.println("Passenger not found!");
                                }
                                break;

                            case 3:
                                List<String> tickets = FileManager.loadFromFile("tickets.txt");
                                if (tickets.isEmpty()) {
                                    System.out.println("No tickets found.");
                                } else {
                                    System.out.println("\nTicketID | PassengerID | BusID | Seat | Fare");
                                    for (String t : tickets) {
                                        System.out.println(t);
                                    }
                                }
                                break;

                            case 4:
                                System.out.print("Enter Passenger ID: ");
                                String historyId = sc.nextLine();
                                List<String> passengerHistory = FileManager.loadFromFile("tickets.txt");
                                boolean historyFound = false;
                                for (String t : passengerHistory) {
                                    String[] parts = t.split(",");
                                    if (parts[1].equalsIgnoreCase(historyId)) {
                                        System.out.println("\nTicket Details:");
                                        System.out.println("TicketID: " + parts[0]);
                                        System.out.println("PassengerID: " + parts[1]);
                                        System.out.println("BusID: " + parts[2]);
                                        System.out.println("Seat No: " + parts[3]);
                                        System.out.println("Fare: " + parts[4]);
                                        historyFound = true;
                                    }
                                }
                                if (!historyFound) {
                                    System.out.println("No history found for this passenger.");
                                }
                                break;

                            case 5:
                                passengerMenuRunning = false;
                                break;

                            case 6:
                                System.out.println("Exiting program...");
                                passengerMenuRunning = false;
                                mainMenuRunning = false;
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                // ------------------- TICKETING MENU -------------------
                case 4:
                    boolean ticketMenuRunning = true;
                    while (ticketMenuRunning) {
                        System.out.println("\n----TICKETING----");
                        System.out.println("1. Book ticket");
                        System.out.println("2. Check Seat Availability");
                        System.out.println("3. Calculate fare");
                        System.out.println("4. Generate passenger ticket");
                        System.out.println("5. Fare collection report");
                        System.out.println("6. Return to Main Menu");
                        System.out.println("7. Exit Program");

                        int ticketChoice = getValidInt(sc, "Choose option: ");
                        switch (ticketChoice) {
                            case 1:
                                System.out.print("Enter Ticket ID: ");
                                String ticketId = sc.nextLine();
                                System.out.print("Enter Passenger ID: ");
                                String passengerIdForTicket = sc.nextLine();
                                System.out.print("Enter Bus ID: ");
                                String busIdForTicket = sc.nextLine();
                                System.out.print("Enter Seat Number: ");
                                String seatNo = sc.nextLine();
                                System.out.print("Enter Fare: ");
                                double fare = sc.nextDouble();
                                sc.nextLine();

                                FileManager.saveToFile(
                                        "tickets.txt",
                                        ticketId + "," + passengerIdForTicket + "," + busIdForTicket + "," + seatNo + "," + fare
                                );
                                System.out.println("Ticket booked successfully!");
                                break;

                            case 2:
                                System.out.print("Enter Bus ID: ");
                                String checkBusId = sc.nextLine();
                                List<String> ticketsList = FileManager.loadFromFile("tickets.txt");
                                int bookedSeats = 0;
                                for (String t : ticketsList) {
                                    String[] parts = t.split(",");
                                    if (parts[2].equalsIgnoreCase(checkBusId)) bookedSeats++;
                                }
                                System.out.println("Seats booked on bus " + checkBusId + ": " + bookedSeats);
                                break;

                            case 3:
                                System.out.print("Enter distance (km): ");
                                double distance = sc.nextDouble();
                                sc.nextLine();
                                System.out.println("Calculated Fare: " + (distance * 2));
                                break;

                            case 4:
                                System.out.print("Enter Passenger ID: ");
                                String passengerSearch = sc.nextLine();
                                List<String> allTickets = FileManager.loadFromFile("tickets.txt");
                                boolean ticketFound = false;
                                for (String t : allTickets) {
                                    String[] parts = t.split(",");
                                    if (parts[1].equalsIgnoreCase(passengerSearch)) {
                                        System.out.println("\nTicket Details:");
                                        System.out.println("Ticket ID:" + parts[0]);
                                        System.out.println("Passenger ID:" + parts[1]);
                                        System.out.println("Bus ID:" + parts[2]);
                                        System.out.println("Seat No:" + parts[3]);
                                        System.out.println("Fare: " + parts[4]);
                                        ticketFound = true;
                                    }
                                }
                                if (!ticketFound) System.out.println("No ticket found for this passenger.");
                                break;

                            case 5:
                                List<String> ticketsAll = FileManager.loadFromFile("tickets.txt");
                                double totalFare = 0;
                                for (String t : ticketsAll) {
                                    String[] parts = t.split(",");
                                    totalFare += Double.parseDouble(parts[4]);
                                }
                                System.out.println("Total Fare Collected: " + totalFare);
                                break;

                            case 6:
                                ticketMenuRunning = false;
                                break;

                            case 7:
                                System.out.println("Exiting program...");
                                ticketMenuRunning = false;
                                mainMenuRunning = false;
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                // ------------------- ROUTE MENU -------------------
                case 5:
                    boolean routeMenuRunning = true;
                    while (routeMenuRunning) {
                        System.out.println("\n----ROUTE MANAGEMENT----");
                        System.out.println("1. Add new route");
                        System.out.println("2. Remove route");
                        System.out.println("3. View route details & schedule");
                        System.out.println("4. View passenger booking history for a route");
                        System.out.println("5. Return to Main Menu");
                        System.out.println("6. Exit Program");

                        int routeChoice = getValidInt(sc, "Choose option: ");
                        switch (routeChoice) {
                            case 1:
                                System.out.print("Enter Route ID: ");
                                String routeIdAdd = sc.nextLine();
                                System.out.print("Enter Start Location: ");
                                String start = sc.nextLine();
                                System.out.print("Enter Destination: ");
                                String destination = sc.nextLine();
                                System.out.print("Enter Time: ");
                                String time = sc.nextLine();

                                FileManager.saveToFile(
                                        "routes.txt",
                                        routeIdAdd + "," + start + "," + destination + "," + time
                                );
                                System.out.println("Route added successfully!");
                                break;

                            case 2:
                                System.out.print("Enter Route ID to remove: ");
                                String removeRouteId = sc.nextLine();
                                List<String> routes = FileManager.loadFromFile("routes.txt");
                                List<String> updatedRoutes = new ArrayList<>();
                                boolean foundRoute = false;
                                for (String r : routes) {
                                    String[] parts = r.split(",");
                                    if (parts[0].equalsIgnoreCase(removeRouteId)) {
                                        foundRoute = true;
                                    } else {
                                        updatedRoutes.add(r);
                                    }
                                }
                                if (foundRoute) {
                                    FileManager.overwriteFile("routes.txt", updatedRoutes);
                                    System.out.println("Route removed successfully!");
                                } else {
                                    System.out.println("Route not found!");
                                }
                                break;

                            case 3:
                                List<String> allRoutes = FileManager.loadFromFile("routes.txt");
                                if (allRoutes.isEmpty()) System.out.println("No routes available.");
                                else {
                                    System.out.println("\nRouteID | Start | Destination | Time");
                                    for (String r : allRoutes) System.out.println(r);
                                }
                                break;

                            case 4:
                                System.out.print("Enter Route ID: ");
                                String searchRouteId = sc.nextLine();
                                List<String> ticketsForRoute = FileManager.loadFromFile("tickets.txt");
                                boolean bookingFound = false;
                                for (String t : ticketsForRoute) {
                                    String[] parts = t.split(",");
                                    // Map Bus -> Route later
                                    System.out.println("Ticket: " + t);
                                    bookingFound = true;
                                }
                                if (!bookingFound) System.out.println("No bookings found for this route.");
                                break;

                            case 5:
                                routeMenuRunning = false;
                                break;

                            case 6:
                                System.out.println("Exiting program...");
                                routeMenuRunning = false;
                                mainMenuRunning = false;
                                break;

                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                // ------------------- EXIT -------------------
                case 6:
                    System.out.println("Exiting program...");
                    mainMenuRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1-6.");
            }
        }

        sc.close();
    }
}
