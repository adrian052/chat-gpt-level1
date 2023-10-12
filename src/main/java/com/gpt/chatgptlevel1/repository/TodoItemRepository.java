package com.gpt.chatgptlevel1.repository;

import com.gpt.chatgptlevel1.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
