package todo.repository;

import todo.model.Todo;

import java.util.*;

public class InMemoryTodoRepository implements TodoRepository {
    private final Map<Integer, Todo> todoMap = new HashMap<>();

    public void save(Todo todo) {
        todoMap.put(todo.getId(), todo);
    }

    public List<Todo> findAll() {
        return new ArrayList<>(todoMap.values());
    }

    public Optional<Todo> findById(int id) {
        return Optional.ofNullable(todoMap.get(id));
    }

    public void delete(int id) {
        todoMap.remove(id);
    }
}
