package com.study.jpa.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jpa.dto.TodoList;

@Repository
public interface TodoRepository extends JpaRepository<TodoList, Long> {
    
    
    List<TodoList> findAll();
    
    Optional<TodoList> findByKey(String key);

    
}