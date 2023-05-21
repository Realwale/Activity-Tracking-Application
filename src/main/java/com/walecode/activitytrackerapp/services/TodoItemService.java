package com.walecode.activitytrackerapp.services;

import com.walecode.activitytrackerapp.models.TodoItem;
import com.walecode.activitytrackerapp.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TodoItemService {

    @Autowired
    private TodoItemRepository todoItemRepository;

    public Optional<TodoItem> getById(Long id) {
        return todoItemRepository.findById(id);
    }
    public TodoItem getTask(){
        User user

    }

    public Iterable<TodoItem> getAll(Long id) {
        return todoItemRepository.findAll();
    }

    public TodoItem save(TodoItem todoItem, Long id) {
        if (todoItem.getId() == null) {
            todoItem.setCreatedAt(Instant.now());
        }
        todoItem.setUpdatedAt(Instant.now());
        return todoItemRepository.save(todoItem);
    }

    public void delete(TodoItem todoItem) {
        todoItemRepository.delete(todoItem);
    }

}
