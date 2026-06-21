package citybus;

import java.io.*;
import java.util.*;

public class FileManager {
    //Append (save new data)
    public static void saveToFile(String fileName, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving data:" + e.getMessage());
        }
    }

    //Load all lines from the file
    public static List<String> loadFromFile(String fileName) {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return list;
    }

    // Rewrite entire file (Used for removing or updating)
    public static void overwriteFile(String fileName, List<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : data) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error rewriting file:" + e.getMessage());
        }

    }
    public static void clearFile(String filename) {
        try (FileWriter fw = new FileWriter(filename, false)) {
            // opening in overwrite mode clears the file
        } catch (IOException e) {
            System.out.println("Error clearing file: " + filename);
        }
    }
    public class Filemanager {

        // Existing method
        public static void saveToFile(String fileName, String data) {
            // existing code
        }

        // OVERLOADED METHOD
        public static void saveToFile(String fileName, String data, boolean newLine) {
            // internally call existing method
            saveToFile(fileName, data);
        }
    }

}




