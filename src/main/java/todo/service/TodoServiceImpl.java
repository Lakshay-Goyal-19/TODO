package todo.service;

import todo.dto.TodoRequest;
import todo.dto.TodoResponse;
import todo.model.Todo;
import todo.repository.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    public void addTodo(TodoRequest request) {
        Todo todo = new Todo(
            request.title,
            request.description,
            request.priority,
            request.dueDate
        );
        repository.save(todo);
    }

    public List<TodoResponse> getAllTodos() {
        return repository.findAll().stream().map(todo -> {
            TodoResponse response = new TodoResponse();
            response.id = todo.getId();
            response.title = todo.getTitle();
            response.description = todo.getDescription();
            response.priority = todo.getPriority();
            response.dueDate = todo.getDueDate();
            response.completed = todo.isCompleted();
            response.createdAt = todo.getCreatedAt();
            return response;
        }).collect(Collectors.toList());
    }

    public void markComplete(int id) {
        repository.findById(id).ifPresent(todo -> {
            todo.setCompleted(true);
            repository.save(todo);
        });
    }

    public void deleteTodo(int id) {
        repository.delete(id);
    }
}
