package com.study.jpa.dto;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Entity (name = "todolist")
@Data
public class TodoList {
  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String todo;
    private boolean finish;
    private String key;
   
    public TodoList () {
        
    }
    
}