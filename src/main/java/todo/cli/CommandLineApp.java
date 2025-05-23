package todo.cli;

import todo.dto.TodoRequest;
import todo.dto.TodoResponse;
import todo.model.Priority;
import todo.repository.InMemoryTodoRepository;
import todo.service.TodoService;
import todo.service.TodoServiceImpl;
import todo.util.ConsoleUtils;
import todo.util.DateUtils;

import java.time.LocalDateTime;
import java.util.List;

public class CommandLineApp {
    private final TodoService todoService = new TodoServiceImpl(new InMemoryTodoRepository());
    private final MenuRenderer menuRenderer = new MenuRenderer();

    public void start() {
        while (true) {
            int choice = menuRenderer.displayMainMenu();
            switch (choice) {
                case 1: addTodo(); break;
                case 2: viewAll(); break;
                case 3: editTodo(); break;
                case 4: deleteTodo(); break;
                case 5: searchTodos(); break;
                case 6: markCompleteOrIncomplete(); break;
                case 7: 
                    menuRenderer.printMessage("Goodbye!");
                    System.exit(0); 
                    break;
                default: ConsoleUtils.printError("Invalid choice");
            }
        }
    }

    private void addTodo() {
        TodoRequest req = new TodoRequest();
        do {
            req.title = menuRenderer.getStringInput("Title: ");
            if (!InputHandler.isValidText(req.title, 100)) {
                ConsoleUtils.printError("Title required (max 100 chars).");
            }
        } while (!InputHandler.isValidText(req.title, 100));

        do {
            req.description = menuRenderer.getStringInput("Description: ");
            if (!InputHandler.isValidText(req.description, 500)) {
                ConsoleUtils.printError("Description required (max 500 chars).");
            }
        } while (!InputHandler.isValidText(req.description, 500));

        String priorityInput;
        do {
            priorityInput = menuRenderer.getStringInput("Priority (LOW/MEDIUM/HIGH): ");
            if (!InputHandler.isValidPriority(priorityInput)) {
                ConsoleUtils.printError("Invalid priority. Try again.");
            }
        } while (!InputHandler.isValidPriority(priorityInput));
        req.priority = InputHandler.parsePriority(priorityInput);

        String dateTimeInput;
        do {
            dateTimeInput = menuRenderer.getStringInput("Due date and time (YYYY-MM-DD HH:mm): ");
            if (!InputHandler.isValidDateTime(dateTimeInput)) {
                ConsoleUtils.printError("Invalid date/time format. Use YYYY-MM-DD HH:mm.");
                continue;
            }
            LocalDateTime dateTime = DateUtils.parseDateTime(dateTimeInput);
            if (dateTime.isBefore(LocalDateTime.now())) {
                ConsoleUtils.printError("Due date/time cannot be in the past.");
                dateTimeInput = null;
            }
        } while (dateTimeInput == null || !InputHandler.isValidDateTime(dateTimeInput));
        req.dueDate = DateUtils.parseDateTime(dateTimeInput);

        try {
            todoService.addTodo(req);
            menuRenderer.printMessage("Todo added successfully.");
        } catch (Exception e) {
            ConsoleUtils.printError(e.getMessage());
        }
    }

    private void viewAll() {
        List<TodoResponse> todos = todoService.getAllTodos();
        if (todos.isEmpty()) {
            menuRenderer.printMessage("No todos found.");
        } else {
            todos.forEach(todo -> menuRenderer.printMessage(
                todo.id + " | " + todo.title + " | " + todo.priority + " | " +
                (todo.completed ? "COMPLETED" : "PENDING") + " | Due: " +
                todo.dueDate.format(DateUtils.DATE_TIME_FORMATTER)));
        }
    }

    private void editTodo() {
        String idStr = menuRenderer.getStringInput("Enter ID to edit: ");
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            ConsoleUtils.printError("Invalid ID format.");
            return;
        }
        List<TodoResponse> todos = todoService.getAllTodos();
        TodoResponse todo = todos.stream().filter(t -> t.id == id).findFirst().orElse(null);
        if (todo == null) {
            ConsoleUtils.printError("Todo not found.");
            return;
        }
        String newTitle = menuRenderer.getStringInput("New Title (leave blank to keep): ");
        if (InputHandler.isValidText(newTitle, 100)) todo.title = newTitle;
        String newDesc = menuRenderer.getStringInput("New Description (leave blank to keep): ");
        if (InputHandler.isValidText(newDesc, 500)) todo.description = newDesc;
        String newPriority = menuRenderer.getStringInput("New Priority (LOW/MEDIUM/HIGH, leave blank to keep): ");
        if (InputHandler.isValidPriority(newPriority)) todo.priority = InputHandler.parsePriority(newPriority);
        String newDueDateTime = menuRenderer.getStringInput("New Due Date and Time (YYYY-MM-DD HH:mm, leave blank to keep): ");
        if (InputHandler.isValidDateTime(newDueDateTime)) {
            LocalDateTime dateTime = DateUtils.parseDateTime(newDueDateTime);
            if (!dateTime.isBefore(LocalDateTime.now())) todo.dueDate = dateTime;
        }
        todoService.deleteTodo(id);
        TodoRequest req = new TodoRequest();
        req.title = todo.title;
        req.description = todo.description;
        req.priority = todo.priority;
        req.dueDate = todo.dueDate;
        todoService.addTodo(req);
        menuRenderer.printMessage("Todo updated.");
    }

    private void deleteTodo() {
        String idStr = menuRenderer.getStringInput("Enter ID to delete: ");
        try {
            int id = Integer.parseInt(idStr);
            List<TodoResponse> todos = todoService.getAllTodos();
            boolean exists = todos.stream().anyMatch(t -> t.id == id);
            if (!exists) {
                ConsoleUtils.printError("Todo not found.");
                return;
            }
            todoService.deleteTodo(id);
            menuRenderer.printMessage("Todo deleted.");
        } catch (Exception e) {
            ConsoleUtils.printError("Invalid ID or error: " + e.getMessage());
        }
    }

    private void searchTodos() {
        String term = menuRenderer.getStringInput("Enter search term: ");
        if (term == null || term.trim().isEmpty()) {
            ConsoleUtils.printError("Search term cannot be empty.");
            return;
        }
        List<TodoResponse> todos = todoService.getAllTodos();
        List<TodoResponse> results = todos.stream()
            .filter(t -> t.title.toLowerCase().contains(term.toLowerCase()) || t.description.toLowerCase().contains(term.toLowerCase()))
            .toList();
        if (results.isEmpty()) {
            menuRenderer.printMessage("No todos found matching: " + term);
        } else {
            results.forEach(todo -> menuRenderer.printMessage(
                todo.id + " | " + todo.title + " | " + todo.priority + " | " +
                (todo.completed ? "COMPLETED" : "PENDING") + " | Due: " +
                todo.dueDate.format(DateUtils.DATE_TIME_FORMATTER)));
        }
    }

    private void markCompleteOrIncomplete() {
        String idStr = menuRenderer.getStringInput("Enter ID: ");
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception e) {
            ConsoleUtils.printError("Invalid ID format.");
            return;
        }
        List<TodoResponse> todos = todoService.getAllTodos();
        TodoResponse todo = todos.stream().filter(t -> t.id == id).findFirst().orElse(null);
        if (todo == null) {
            ConsoleUtils.printError("Todo not found.");
            return;
        }
        String action = menuRenderer.getStringInput("Mark as (C)omplete or (I)ncomplete? ");
        if (action.equalsIgnoreCase("C")) {
            if (todo.completed) {
                menuRenderer.printMessage("Todo is already completed.");
            } else {
                todoService.markComplete(id);
                menuRenderer.printMessage("Todo marked as complete.");
            }
        } else if (action.equalsIgnoreCase("I")) {
            if (!todo.completed) {
                menuRenderer.printMessage("Todo is already incomplete.");
            } else {
                todoService.deleteTodo(id);
                TodoRequest req = new TodoRequest();
                req.title = todo.title;
                req.description = todo.description;
                req.priority = todo.priority;
                req.dueDate = todo.dueDate;
                todoService.addTodo(req);
                menuRenderer.printMessage("Todo marked as incomplete.");
            }
        } else {
            ConsoleUtils.printError("Invalid action.");
        }
    }
}
