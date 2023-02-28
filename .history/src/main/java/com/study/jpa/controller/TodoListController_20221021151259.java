package com.study.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.jpa.dto.TodoList;
import com.study.jpa.dto.TodoUpdDTO;
import com.study.jpa.result.ErrorResponse;
import com.study.jpa.result.ResultCodeImpl;
import com.study.jpa.service.TodoListService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/todolist")
@RestController
@RequiredArgsConstructor
public class TodoListController {
    
    @NonNull
    TodoListService todoListService;
    
    @GetMapping("/all")
    public ErrorResponse getTodoList() {
        List<TodoList> todolist = todoListService.findAll ();
        
        if(todolist.isEmpty ()) {
            return new ErrorResponse( ResultCodeImpl.FAIL);
        }
        
        return new ErrorResponse( ResultCodeImpl.SUCCESS, todolist );
    }
    
    @PostMapping()
    public ErrorResponse postTodo(@RequestBody  TodoList todo) {
        TodoList todoItem = todoListService.save ( todo );
 
        if(todoItem == null) {
            return new ErrorResponse( ResultCodeImpl.FAIL);
        }
        
        
        return new ErrorResponse( ResultCodeImpl.SUCCESS, "등록 완료", todoItem);
    }
    
    @PutMapping("/{key}")
    public ErrorResponse putTodo(@PathVariable String key,
                          @RequestBody TodoUpdDTO todo) {
        
        Optional<TodoList> todoItem = todoListService.putTodo ( key, todo );
        
        if(todoItem == null) {
            return new ErrorResponse( ResultCodeImpl.INVALID_KEY);
        }
        
        return new ErrorResponse( ResultCodeImpl.SUCCESS, " 수정 완료", todoItem );
    }
    
    @DeleteMapping("/{keyList}")
    public ErrorResponse delete(@PathVariable("keyList") List<String> keyList){
        
        if( !keyList.isEmpty()) {
            
            for(String key : keyList) {
                todoListService.delete( key );   
            }
        }
        else {
            return new ErrorResponse(ResultCodeImpl.RESOURCE_NOT_FOUND);
        }
        
        return new ErrorResponse( ResultCodeImpl.SUCCESS, "삭제 완료");
    }
    
    @DeleteMapping("/all")
    public ErrorResponse delTodoList() {
        todoListService.deleteAll ();
        
        List<TodoList> todoList = todoListService.findAll();
        
        if( todoList.isEmpty () ) {
            return new ErrorResponse( ResultCodeImpl.SUCCESS, "전체 삭제 완료");
        }
        return new ErrorResponse( ResultCodeImpl.FAIL);
    }
}