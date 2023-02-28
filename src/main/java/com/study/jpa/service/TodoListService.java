package com.study.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.jpa.dto.TodoList;
import com.study.jpa.dto.TodoUpdDTO;

@Service
public interface TodoListService {
    Optional<TodoList> putTodo(String key, TodoUpdDTO boardUpdDTO);
    
    List<TodoList> findAll();
    
    TodoList save(TodoList todo);
    
    int delete(String key);
    
    void deleteAll();
}