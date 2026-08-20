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
            IO.println("Number must be positive. Try again:");
            number = scanner.nextInt();
            scanner.nextLine();
        }

        return number;
    }

    public double readDecimal(String message) {
        IO.println(message);

        double number = scanner.nextDouble();
        scanner.nextLine();

        while (!HelperUtils.isPositive(number)) {
            IO.println("Number must be positive. Try again:");
            number = scanner.nextDouble();
            scanner.nextLine();
        }

        return number;
    }
}

