package todo.dto;

import todo.model.Priority;
import java.time.LocalDateTime;

public class TodoResponse {
    public int id;
    public String title;
    public String description;
    public Priority priority;
    public LocalDateTime dueDate;
    public boolean completed;
    public LocalDateTime createdAt;
}
