package edu.curtin.oose2024s1.assignment2.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

    private static String fileName = "sim_results.txt";

    // Method to write data to file
    public static void writeToFile(String data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();

        } catch (IOException e) {

            System.out.println("An error occurred while writing to file." + e.getMessage());

        }

    }

    // Method to ensure the message is valid
    public static boolean isValidMessage(String message) {
        boolean valid = true;
        String[] parts = message.split("[-\\s]+");
        if (!(parts[0].equals("DELIVERY")) && !(parts[0].equals("DROP")) && !(parts[0].equals("PURCHASE"))
                && !(parts[0].equals("PICK"))) {
            valid = false;
        }

        return valid;
    }

}
