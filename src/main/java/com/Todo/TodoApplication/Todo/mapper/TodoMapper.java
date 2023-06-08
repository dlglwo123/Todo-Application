package com.Todo.TodoApplication.Todo.mapper;

import com.Todo.TodoApplication.Todo.dto.TodoDto;
import com.Todo.TodoApplication.Todo.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo PostDtoToEntity(TodoDto.Post post);
    Todo PatchDtoToEntity(TodoDto.Patch patch);
    TodoDto.Response EntityToResponse(Todo todo);
    List<TodoDto.Response> ListOfEntityToResponses(List<Todo> todoList);
}
