package todo.cli;

import todo.util.ConsoleUtils;

public class MenuRenderer {
    public int displayMainMenu() {
        ConsoleUtils.printLine("\n--- TODO APP MENU ---");
        ConsoleUtils.printLine("1. Add Todo");
        ConsoleUtils.printLine("2. View All Todos");
        ConsoleUtils.printLine("3. Edit Todo");
        ConsoleUtils.printLine("4. Delete Todo");
        ConsoleUtils.printLine("5. Search Todos");
        ConsoleUtils.printLine("6. Mark Complete/Incomplete");
        ConsoleUtils.printLine("7. Exit");
        String input = ConsoleUtils.readLine("Select option: ");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            ConsoleUtils.printError("Invalid input! Please enter a number.");
            return -1;
        }
    }

    public String getStringInput(String prompt) {
        return ConsoleUtils.readLine(prompt);
    }

    public void printMessage(String message) {
        ConsoleUtils.printLine(message);
    }
}
