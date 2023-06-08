package com.Todo.TodoApplication.Todo.mapper;

import com.Todo.TodoApplication.Todo.dto.TodoDto.Patch;
import com.Todo.TodoApplication.Todo.dto.TodoDto.Post;
import com.Todo.TodoApplication.Todo.dto.TodoDto.Response;
import com.Todo.TodoApplication.Todo.entity.Todo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T11:05:43+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo PostDtoToEntity(Post post) {
        if ( post == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setTitle( post.getTitle() );
        todo.setTodoOrder( post.getTodoOrder() );
        todo.setCompleted( post.isCompleted() );

        return todo;
    }

    @Override
    public Todo PatchDtoToEntity(Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setId( patch.getId() );
        todo.setTitle( patch.getTitle() );
        todo.setTodoOrder( patch.getTodoOrder() );
        todo.setCompleted( patch.isCompleted() );

        return todo;
    }

    @Override
    public Response EntityToResponse(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        Response response = new Response();

        if ( todo.getId() != null ) {
            response.setId( todo.getId() );
        }
        response.setTitle( todo.getTitle() );
        response.setTodoOrder( todo.getTodoOrder() );
        response.setCompleted( todo.isCompleted() );

        return response;
    }

    @Override
    public List<Response> ListOfEntityToResponses(List<Todo> todoList) {
        if ( todoList == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( todoList.size() );
        for ( Todo todo : todoList ) {
            list.add( EntityToResponse( todo ) );
        }

        return list;
    }
}
