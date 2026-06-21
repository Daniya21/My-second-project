package citybus;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {

    private static  String USER_FILE = "users.txt";

    public static boolean loginPage() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");  // <-- Added Exit option
        System.out.print("Choose: ");

        int choice;

        try {
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            sc.nextLine(); // clear invalid input
            return loginPage(); // retry
        }

        switch (choice) {
            case 1:
                register();
                return login();
            case 2:
                return login();
            case 3:
                System.out.println("Exiting program. Goodbye!");
                return false;  // <-- Stop the program
            default:
                System.out.println("Invalid choice! Please select 1, 2, or 3.");
                return loginPage(); // retry
        }
    }

    private static void register() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter new username: ");
        String username = sc.nextLine();

        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        FileManager.saveToFile(USER_FILE, username + "," + password);
        System.out.println("Registration successful!");
    }

    private static boolean login() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String u = sc.nextLine();

        System.out.print("Enter password: ");
        String p = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(u) && data[1].equals(p)) {
                    System.out.println("Login successful!");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file.");
        }

        System.out.println("Login failed! Try again.");
        return login();
    }
}
