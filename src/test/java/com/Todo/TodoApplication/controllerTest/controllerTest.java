package com.Todo.TodoApplication.controllerTest;

import com.Todo.TodoApplication.Todo.dto.TodoDto;
import com.Todo.TodoApplication.Todo.entity.Todo;
import com.Todo.TodoApplication.Todo.mapper.TodoMapper;
import com.Todo.TodoApplication.Todo.service.TodoService;
import com.Todo.TodoApplication.helper.DocumentHelper;
import com.Todo.TodoApplication.helper.StubData;
import com.Todo.TodoApplication.helper.TodoControllerTestHelper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpMethod;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(RestDocumentationExtension.class)
public class controllerTest implements DocumentHelper, TodoControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    private ResultActions resultActions;
    @MockBean
    private TodoService todoService;
    @MockBean
    private TodoMapper todoMapper;
    private TodoDto.Post post;
    private TodoDto.Response response;
    private String content;
    private URI uri;
    private long Id = 1L;
    private Todo todo;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)).build();

        todo = new Todo(1L,"운동하기","1",true);
    }

    @Test
    @DisplayName("Todo 등록 테스트")
    public void TodoPostTest() throws Exception {

        post = (TodoDto.Post) StubData.MockTodo.getRequestBody(HttpMethod.POST);
        response = (TodoDto.Response) StubData.MockTodo.getResponseBody(HttpMethod.GET);
        content = gson.toJson(post);
        uri = getUri();

        given(todoMapper.PostDtoToEntity(Mockito.any(TodoDto.Post.class))).willReturn(new Todo());
        given(todoService.createTodo(Mockito.any(Todo.class))).willReturn(new Todo());
        given(todoMapper.EntityToResponse(Mockito.any(Todo.class))).willReturn(response);

        resultActions = mockMvc.perform(
                postRequestBuilder(uri,content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.todoOrder").value(post.getTodoOrder()));

        postDocument(resultActions);
    }

    @Test
    @DisplayName("Todo 수정 테스트")
    public void TodoPatchTest() throws  Exception {

        TodoDto.Patch patch = (TodoDto.Patch) StubData.MockTodo.getRequestBody(HttpMethod.PATCH);
        response = (TodoDto.Response) StubData.MockTodo.getResponseBody(HttpMethod.GET);
        content = gson.toJson(patch);

        given(todoMapper.PatchDtoToEntity(Mockito.any(TodoDto.Patch.class))).willReturn(new Todo());
        given(todoService.updateTodo(Mockito.any(Todo.class))).willReturn(new Todo());
        given(todoMapper.EntityToResponse(Mockito.any(Todo.class))).willReturn(response);

        resultActions = mockMvc.perform(
                        patchRequestBuilder(content,Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.todoOrder").value(patch.getTodoOrder()));

        patchDocument(resultActions);
    }

    @Test
    @DisplayName("Todo 조회 테스트")
    public void TodoGetTest() throws Exception {

        response = (TodoDto.Response) StubData.MockTodo.getResponseBody(HttpMethod.GET);

        given(todoService.getTodo(Mockito.anyLong())).willReturn(new Todo());
        given(todoMapper.EntityToResponse(Mockito.any(Todo.class))).willReturn(response);

        resultActions = mockMvc.perform(
                getRequestBuilder(Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(todo.getTitle()))
                .andExpect(jsonPath("$.todoOrder").value(todo.getTodoOrder()));

        getDocument(resultActions);
    }

    @Test
    @DisplayName("TodoList 조회 테스트")
    public void ListOfTodoGetTest() throws Exception {

        uri = getUri();

        //Response
        List<Integer> key = new ArrayList<>();
        key.add(1);
        key.add(2);

        Page<Todo> todoPage =
                StubData.MockTodo.getPageOfRequestBody(key);

        List<TodoDto.Response> responses
                = StubData.MockTodo.getListOfResponse(key);

        // params 설정
        String page = "1";
        String size = "10";
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("page",page);
        params.add("size",size);

        given(todoService.getTodos(Mockito.anyInt(),Mockito.anyInt())).willReturn(todoPage);
        given(todoMapper.ListOfEntityToResponses(Mockito.anyList())).willReturn(responses);

        resultActions = mockMvc.perform(
                getListOfRequestBuilder(uri,params))
                .andExpect(status().isOk());

        getPageOfDocuments(resultActions);
    }

    @Test
    @DisplayName("Todo 삭제 테스트")
    public void DeleteTodo() throws Exception {

        resultActions = mockMvc.perform(
                        deleteRequestBuilder(Id))
                .andExpect(status().isNoContent());

        deleteDocument(resultActions);
    }
}
