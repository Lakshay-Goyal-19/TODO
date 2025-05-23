package todo.model;

import java.time.LocalDateTime;

public class Todo {
    private static int counter = 1;
    private int id;
    private String title;
    private String description;
    private Priority priority;
    private LocalDateTime dueDate;
    private boolean completed;
    private LocalDateTime createdAt;

    public Todo(String title, String description, Priority priority, LocalDateTime dueDate) {
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Priority getPriority() { return priority; }
    public LocalDateTime getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
