package todo.util;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void printLine(String message) {
        System.out.println(message);
    }

    public static void printError(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }
}
