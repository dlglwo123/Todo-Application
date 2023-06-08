package com.Todo.TodoApplication.Todo.service;

import com.Todo.TodoApplication.Todo.entity.Todo;
import com.Todo.TodoApplication.Todo.repositoy.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){

        verifiedExistTitle(todo.getTitle());
        verifiedExistTodoOrder(todo.getTodoOrder());

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo){

        Todo findTodo = findExistId(todo.getId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(findTodo::setTitle);
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(findTodo::setTodoOrder);
        Optional.of(todo.isCompleted())
                .ifPresent(findTodo::setCompleted);

        return todoRepository.save(findTodo);
    }

    public Todo getTodo(long id){

        Todo findTodo = findExistId(id);
        return todoRepository.save(findTodo);
    }

    public Page<Todo> getTodos(int page, int size){

        return todoRepository.findAll(
                PageRequest.of(page,size,Sort.by("id")));
    }

    public void deleteTodos(long id){

        todoRepository.deleteById(id);
        log.info("할일 삭제 완료");
    }

    private Todo findExistId(long id){

        Optional<Todo> optionalTodo = todoRepository.findById(id);

        return optionalTodo.orElseThrow(
                () -> new RuntimeException()
        );
    }

    private void verifiedExistTitle(String title){

        Optional<Todo> optionalTodo = todoRepository.findByTitle(title);

        if(optionalTodo.isPresent()){
            throw new RuntimeException();
        }
    }
    private void verifiedExistTodoOrder(String todoOrder){

        Optional<Todo> optionalTodo = todoRepository.findByTodoOrder(todoOrder);

        if(optionalTodo.isPresent()){
            throw new RuntimeException();
        }

    }

}
