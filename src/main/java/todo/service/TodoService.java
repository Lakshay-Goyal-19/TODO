package todo.service;

import todo.dto.TodoRequest;
import todo.dto.TodoResponse;

import java.util.List;

public interface TodoService {
    void addTodo(TodoRequest request);
    List<TodoResponse> getAllTodos();
    void markComplete(int id);
    void deleteTodo(int id);
}
