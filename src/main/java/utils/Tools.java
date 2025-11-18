package utils;

import java.util.Scanner;

public class Tools {

    private Tools() {
    }

    public static double validateDouble(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String userStr = input.nextLine().trim().replace(',', '.');

            try {
                return Double.parseDouble(userStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid decimal value.");
            }
        }
    }

    public static int validateInt(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String userStr = input.nextLine().trim();
            try {
                return Integer.parseInt(userStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static String validateString(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String string = input.nextLine();
            if (string.matches("^[A-Za-zÆØÅæøå0-9\\s\\-]+$")) {
                return string;
            } else {
                System.out.println("Invalid string. Please enter a valid string.");
            }
        }
    }

    public static void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static void waitForUser(Scanner input) {
        System.out.print("\nPress enter to continue...");
        input.nextLine();
    }
}
