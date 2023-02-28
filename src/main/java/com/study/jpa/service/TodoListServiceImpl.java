package com.study.jpa.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.study.jpa.dto.TodoList;
import com.study.jpa.dto.TodoUpdDTO;
import com.study.jpa.repository.TodoRepository;


import lombok.AllArgsConstructor;
import lombok.NonNull;

@Service
@AllArgsConstructor
public class TodoListServiceImpl implements TodoListService
{
    private final Logger log = LoggerFactory.getLogger ( this.getClass().getSimpleName () );
    
    @NonNull
    private TodoRepository todoListRepository;

    /**
     * 투두 아이템을 수정하는 메소드
     * 
     */
    @Override
    public Optional<TodoList> putTodo ( String key,
                                        TodoUpdDTO todoUpdDTO )
    {   
        //key에 맞는 투두아이템을 찾아
        Optional<TodoList> entity = this.todoListRepository.findByKey(key);
        // ifPresnet는 컨슈머를 매개변수로 입력받아서 객체가 존재할 때만 실행하는 Optional 메소드이다.
        entity.ifPresent (t -> {
            //내용이 null 이 아니라면 엔티티의 객체를 변경한다.
            if(todoUpdDTO.getTodo() != null) {
                t.setTodo ( todoUpdDTO.getTodo () );
                t.setFinish ( todoUpdDTO.isFinish () );
            }
            //이것을 실행하면 PRIMARY 키의 값이 같기에 UPDATE 가 실행될 것이다. 
            this.todoListRepository.save(t);
        });

        log.info("=======putTodo - t=======" + entity);
        
        return entity;
    }

    /**
     * 
     * 전체 투두를 출력하는 메소드
     * 
     */
    @Override
    public List<TodoList> findAll ()
    {
        List<TodoList> todoList= todoListRepository.findAll ();
        
        log.info("=======findAll - todolist=======" + todoList);
        
        return todoList;
    }

    /**
     * 
     * 투두를 저장하는 메소드
     * 
     */
    @Override
    public TodoList save ( TodoList todo )
    {
        log.info("=======save - 받아온 todo 인자=======" + todo);
        
        TodoList todoItem = todoListRepository.save ( todo );
        
        log.info("=======save - todoitem=======" + todoItem);
        
        return todoItem;
    }

    /**
     * 
     * 투두 삭제하는 메소드
     * 
     */
    @Override
    public int delete ( String key )
    {
        Optional<TodoList> todoItem = todoListRepository.findByKey ( key );
        
        log.info("=======save - todoitem=======" + todoItem);
        
        if(todoItem.isPresent ()) {
            todoListRepository.delete(todoItem.get ());
            return 1;
        }
        
        return 0;
    }

    /**
     * 
     * 투두 전체 삭제하는 메소드
     * 
     */
    @Override
    public void deleteAll()
    {
        todoListRepository.deleteAllInBatch();
        
    }
    
}
