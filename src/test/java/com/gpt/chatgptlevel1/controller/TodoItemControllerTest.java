package com.gpt.chatgptlevel1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gpt.chatgptlevel1.entity.TodoItem;
import com.gpt.chatgptlevel1.service.TodoItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoItemControllerTest {

    @Mock
    private TodoItemService todoItemService;

    @InjectMocks
    private TodoItemController todoItemController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoItemController).build();
    }

    @Test
    public void testGetTodoItemById() throws Exception {
        Long id = 1L;
        TodoItem todoItem = new TodoItem();
        Mockito.when(todoItemService.getTodoItemById(id)).thenReturn(Optional.of(todoItem));

        mockMvc.perform(get("/api/todo/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllTodoItems() throws Exception {
        List<TodoItem> todoItems = new ArrayList<>();
        Mockito.when(todoItemService.getAllTodoItems()).thenReturn(todoItems);

        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem();
        String todoItemJson = objectMapper.writeValueAsString(todoItem);

        Mockito.when(todoItemService.createTodoItem(Mockito.any(TodoItem.class))).thenReturn(todoItem);

        mockMvc.perform(post("/api/todo")
                        .contentType("application/json")
                        .content(todoItemJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTodoItem() throws Exception {
        Long id = 1L;
        TodoItem updatedTodoItem = new TodoItem();
        String updatedTodoItemJson = objectMapper.writeValueAsString(updatedTodoItem);

        Mockito.when(todoItemService.updateTodoItem(id, updatedTodoItem)).thenReturn(updatedTodoItem);

        mockMvc.perform(put("/api/todo/" + id)
                        .contentType("application/json")
                        .content(updatedTodoItemJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTodoItem() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/todo/" + id))
                .andExpect(status().isOk());
    }
}
