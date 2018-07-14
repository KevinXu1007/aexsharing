package com.aexsharing.api.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoTodoServiceImpl implements TodoService{
    private final TodoRepository repository;

    @Autowired
    MongoTodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo addTodo(Todo todo) {
        return repository.insert(todo);
    }

    @Override
    public Todo deleteTodo(String id) {
        Optional<Todo> deletedTodo = repository.findById(id);
        repository.deleteById(id);
        return deletedTodo.orElse(null);
    }

    @Override
    public List<Todo> findAll(String username) {
        return repository.findByUserUsername(username);
    }

    @Override
    public Todo findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Todo update(Todo todo) {
        repository.save(todo);
        return todo;
    }
}
