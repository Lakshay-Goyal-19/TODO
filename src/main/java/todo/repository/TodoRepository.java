package todo.repository;

import todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    void save(Todo todo);
    List<Todo> findAll();
    Optional<Todo> findById(int id);
    void delete(int id);
}
