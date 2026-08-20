package utils;
import java.util.Scanner;
public class InputHandler {
    private Scanner scanner = new Scanner(System.in);
    public String readTaxt(String message) {
    IO.println(message);
        String text = scanner.nextLine();

        while (!HelperUtils.isValidText(text)) {
            IO.println("Invalid text. Try again:");
            text = scanner.nextLine();
        }

        return text;
    }

    public int readNumber(String message) {
        IO.println(message);

        int number = scanner.nextInt();
        scanner.nextLine();

        while (!HelperUtils.isPositive(number)) {
            IO.println("-Number must be positive. Try again:");
            number = scanner.nextInt();
            scanner.nextLine();
        }

        return number;
    }

    public double readDecimal(String message) {
        IO.println(message);

        double number  = scanner.nextDouble();
        scanner.nextLine();

        while (!HelperUtils.isPositive(number)) {
            IO.println("Number must be positive. Try again:");
            number = scanner.nextDouble();
            scanner.nextLine();
        }

        return number;
    }
    public int readNumber(String message, int min, int max) {

        IO.println(message);

        int number = scanner.nextInt();
        scanner.nextLine();

        while (!HelperUtils.isInRange(number, min, max)) {
            IO.println("Enter a number between " + min + " and " + max + ":");

            number = scanner.nextInt();
            scanner.nextLine();
        }

        return number;
    }

    public boolean readConfirmation(String message) {

        IO.println(message + " (yes/no)");

        String answer = scanner.nextLine();

        while (!HelperUtils.isOneOf(answer,
                new String[]{"yes", "no"})) {

            IO.println("Please enter yes or no:");
            answer = scanner.nextLine();
        }

        return answer.equals("yes");
    }

    public String readOneOf(String message, String[] allowed) {

        IO.println(message);

        String value = scanner.nextLine();

        while (!HelperUtils.isOneOf(value, allowed)) {

            IO.println("Invalid choice. Try again:");
            value = scanner.nextLine();
        }

        return value;
    }
}

