package com.Todo.TodoApplication.helper;

import com.Todo.TodoApplication.Todo.dto.TodoDto;
import com.Todo.TodoApplication.Todo.entity.Todo;
import com.Todo.TodoApplication.Todo.repositoy.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubData {

    private static Map<HttpMethod, Object> stubTodoRequestBody;
    private static Map<HttpMethod, Object> stubTodoResponseBody;
    private static Map<Integer, TodoDto.Response> stubListOfTodoResponsesBody;
    private static Map<Integer, Todo> stubPageTodoRequestBody;
    static {
        //RequestBody
        stubTodoRequestBody = new HashMap<>();
        stubTodoRequestBody.put(HttpMethod.POST, new TodoDto.Post("운동하기","1",false));
        stubTodoRequestBody.put(HttpMethod.PATCH, new TodoDto.Patch(1,"운동하기","1",true));
        //ResponseBody
        stubTodoResponseBody = new HashMap<>();
        stubTodoResponseBody.put(HttpMethod.GET, new TodoDto.Response(1,"운동하기","1",true));
        //ListOfResponseBody
        stubListOfTodoResponsesBody = new HashMap<>();
        stubListOfTodoResponsesBody.put(1, new TodoDto.Response(1,"운동하기","1",true));
        stubListOfTodoResponsesBody.put(2, new TodoDto.Response(2,"책읽기","2",true));
        //PageTodoRequestBody
        stubPageTodoRequestBody = new HashMap<>();
        stubPageTodoRequestBody.put(1, new Todo(1L,"운동하기","1",true));
        stubPageTodoRequestBody.put(2, new Todo(2L,"책읽기","2",true));
    }

    public static class MockTodo{

        public static Object getRequestBody(HttpMethod httpMethod){
            return stubTodoRequestBody.get(httpMethod);
        }
        public static Object getResponseBody(HttpMethod httpMethod){
            return stubTodoResponseBody.get(httpMethod);
        }
        public static Page<Todo> getPageOfRequestBody(List<Integer> key){
            return new PageImpl<>(
                    List.of(stubPageTodoRequestBody.get(key.get(0)),
                            stubPageTodoRequestBody.get(key.get(1))),
                    PageRequest.of(0,10,Sort.by("id").descending()),2
            );
        }
        public static List<TodoDto.Response> getListOfResponse(List<Integer> key){
            return List.of(stubListOfTodoResponsesBody.get(key.get(0)),
                    stubListOfTodoResponsesBody.get(key.get(1)));
        }
    }
}
