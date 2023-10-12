package com.gpt.chatgptlevel1.service;

import com.gpt.chatgptlevel1.entity.TodoItem;
import com.gpt.chatgptlevel1.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {
    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    // Implement methods for creating, reading, updating, and deleting todo items
    public TodoItem createTodoItem(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public List<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> getTodoItemById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem updateTodoItem(Long id, TodoItem updatedTodoItem) {
        if (todoItemRepository.existsById(id)) {
            updatedTodoItem.setId(id);
            return todoItemRepository.save(updatedTodoItem);
        }
        return null;
    }

    public void deleteTodoItem(Long id) {
        if (todoItemRepository.existsById(id)) {
            todoItemRepository.deleteById(id);
        }
    }
}
