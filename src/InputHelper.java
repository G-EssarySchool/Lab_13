import java.util.Scanner;

public class InputHelper {

    //getNonZeroLengthString method: returns a string of non-zero length to the user.
    public static String getNonZeroLengthString(Scanner scan, String prompt) {
        boolean valid = false;
        String userInput;

        System.out.println(prompt);
        do {
            userInput = scan.nextLine();
            if (userInput.length() > 0) {
                valid = true;
            }
            else {
                System.out.println("Error - must enter string of non-zero length.");
            }
        } while (!valid);
        return userInput;
    }
    //getInt method: This method should return a valid int to the user.
    public static int getInt(Scanner scan, String prompt) {
        boolean valid = false;
        int userInput = 0;

        System.out.println(prompt);
        do {
            if (scan.hasNextInt()) {
                userInput = scan.nextInt();
                valid = true;
            } else {
                System.out.println("Error - must enter integer value");
                scan.nextLine();
            }
        } while (!valid);
        return userInput;
    }
    //getRangedInt method: This method should return a valid int to the user within a given range.
    public static int getRangedInt(Scanner scan, String prompt, int min, int max) {
        boolean valid = false;
        int userInput = 0;

        System.out.println(prompt);
        do {
            if (scan.hasNextInt()) {
                userInput = scan.nextInt();
                if (userInput >= min && userInput <= max) {
                    valid = true;
                }
                else {
                    System.out.println("Error - input must be between " + min + " and " + max + ".");
                    scan.nextLine();
                }
            }
            else {
                System.out.println("Error - must enter integer value");
                scan.nextLine();
            }
        } while (!valid);
        return userInput;
    }
    //getDouble method: This method should return a valid int to the user.
    public static double getDouble(Scanner scan, String prompt) {
        boolean valid = false;
        double userInput = 0;

        System.out.println(prompt);
        do {
            if (scan.hasNextDouble()) {
                userInput = scan.nextDouble();
                valid = true;
            } else {
                System.out.println("Error - must enter double value");
                scan.nextLine();
            }
        } while (!valid);
        return userInput;
    }
    //getRangedDouble method: This method should return a valid double to the user within a given range.
    public static double getRangedDouble(Scanner scan, String prompt, double min, double max) {
        boolean valid = false;
        double userInput = 0;

        System.out.println(prompt);
        do {
            if (scan.hasNextDouble()) {
                userInput = scan.nextDouble();
                if (userInput >= min && userInput <= max) {
                    valid = true;
                }
                else {
                    System.out.println("Error - input must be between " + min + " and " + max + ".");
                    scan.nextLine();
                }
            }
            else {
                System.out.println("Error - must enter double value");
                scan.nextLine();
            }
        } while (!valid);
        return userInput;
    }
    //getYNConfirm method: displays a yes or no statement and returns the user input.
    public static String getYNConfirm(Scanner scan, String prompt) {
        String userInput = "";
        boolean validContinueChoice = false;
        boolean continueYN;

        System.out.println("Continue? [Y , N]");

        do {
            userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("y")) {
                continueYN = true;
                validContinueChoice = true;
            }
            else if (userInput.equalsIgnoreCase("n")) {
                continueYN = false;
                validContinueChoice = true;
            }
            else {
                System.out.println("Invalid input. Please try again");
            }
        } while (!validContinueChoice);
        return userInput;
    }
    //getPositiveNonZeroInt method: returns a positive non-zero int.
    public static int getPositiveNonZeroInt(Scanner scan, String prompt) {
        boolean valid = false;
        int userInput;

        System.out.println(prompt);
        do {
            userInput = scan.nextInt();
            if (userInput > -1) {
                valid = true;
            } else {
                System.out.println("Error - Must enter positive integer");
            }

        } while (!valid);
        return userInput;
    }
    //getRegExString method: returns a string that matches the RegEx pattern given.
    public static String getRegExString(Scanner scan, String prompt, String regExPattern) {
        boolean valid = false;
        String userInput;

        System.out.println(prompt);
        do {
            userInput = scan.nextLine();
            if (userInput.matches(regExPattern)) {
                valid = true;
            }
            else {
                System.out.println("Error - must match specified pattern");
                //scan.nextLine();
            }
        } while (!valid);
        return userInput;
    }
}