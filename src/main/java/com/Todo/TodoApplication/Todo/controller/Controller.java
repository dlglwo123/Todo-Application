package com.Todo.TodoApplication.Todo.controller;


import com.Todo.TodoApplication.Todo.dto.MultiResponseDto;
import com.Todo.TodoApplication.Todo.dto.TodoDto;
import com.Todo.TodoApplication.Todo.entity.Todo;
import com.Todo.TodoApplication.Todo.mapper.TodoMapper;
import com.Todo.TodoApplication.Todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todos")
@Validated
public class Controller {

    private final TodoService todoService;

    private final TodoMapper mapper;

    public Controller(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoDto.Post post){

        Todo todo = todoService.createTodo(mapper.PostDtoToEntity(post));

        return new ResponseEntity<>(mapper.EntityToResponse(todo), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@Valid @RequestBody TodoDto.Patch patch,
                                    @Positive @PathVariable("id") long id){
        patch.setId(id);

        Todo todo = todoService.updateTodo(mapper.PatchDtoToEntity(patch));

        return new ResponseEntity<>(mapper.EntityToResponse(todo),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@Positive @PathVariable("id") long id){

        return new ResponseEntity<>(mapper.EntityToResponse(todoService.getTodo(id)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size){

        Page<Todo> todoPage = todoService.getTodos(page - 1, size);
        List<Todo> todoList = todoPage.getContent();

        return new ResponseEntity<>(mapper.ListOfEntityToResponses(todoList), HttpStatus.OK);
        //return new ResponseEntity<>(new MultiResponseDto<>(mapper.ListOfEntityToResponses(todoList),todoPage), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@Positive @PathVariable("id") long id){

        todoService.deleteTodos(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
