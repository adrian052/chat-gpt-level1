package com.gpt.chatgptlevel1.service;

import com.gpt.chatgptlevel1.entity.TodoItem;
import com.gpt.chatgptlevel1.repository.TodoItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TodoItemServiceTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemService todoItemService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTodoItem() {
        TodoItem todoItem = new TodoItem();
        when(todoItemRepository.save(todoItem)).thenReturn(todoItem);
        TodoItem createdTodoItem = todoItemService.createTodoItem(todoItem);
        assertNotNull(createdTodoItem);
    }

    @Test
    public void testGetTodoItemById() {
        Long id = 1L;
        TodoItem todoItem = new TodoItem();
        when(todoItemRepository.findById(id)).thenReturn(Optional.of(todoItem));
        Optional<TodoItem> foundTodoItem = todoItemService.getTodoItemById(id);
        assertEquals(todoItem, foundTodoItem.orElse(null));
    }

    @Test
    public void testGetAllTodoItems() {
        List<TodoItem> todoItems = new ArrayList<>();
        when(todoItemRepository.findAll()).thenReturn(todoItems);
        List<TodoItem> allTodoItems = todoItemService.getAllTodoItems();
        assertEquals(0, allTodoItems.size());
    }

    @Test
    public void testUpdateTodoItem() {
        Long id = 1L;
        TodoItem updatedTodoItem = new TodoItem();
        when(todoItemRepository.existsById(id)).thenReturn(true);
        when(todoItemRepository.save(updatedTodoItem)).thenReturn(updatedTodoItem);

        TodoItem updatedItem = todoItemService.updateTodoItem(id, updatedTodoItem);
        assertNotNull(updatedItem);
    }

    @Test
    public void testDeleteTodoItem() {
        Long id = 1L;
        when(todoItemRepository.existsById(id)).thenReturn(true);

        // You may need to change the behavior depending on how you handle deletions
        todoItemService.deleteTodoItem(id);
    }
}
