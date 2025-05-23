package todo.dto;

import todo.model.Priority;
import java.time.LocalDateTime;

public class TodoRequest {
    public String title;
    public String description;
    public Priority priority;
    public LocalDateTime dueDate;
}
