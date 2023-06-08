package com.Todo.TodoApplication.helper;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public interface DocumentHelper {

    default void postDocument(ResultActions resultActions) throws Exception {

        resultActions
                .andDo(
                        document(
                                "post-Todo",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("할일 제목"),
                                        fieldWithPath("todoOrder").type(JsonFieldType.STRING).description("할일 순서"),
                                        fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("할일 성공 유무")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("할일 제목"),
                                        fieldWithPath("todoOrder").type(JsonFieldType.STRING).description("할일 순서"),
                                        fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("할일 성공 유무")
                                )));
    }

    default void patchDocument(ResultActions resultActions) throws Exception {

        resultActions
                .andDo(
                        document(
                                "patch-Todo",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("id").description("식별자")
                                ),
                                requestFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("할일 제목"),
                                        fieldWithPath("todoOrder").type(JsonFieldType.STRING).description("할일 순서"),
                                        fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("할일 성공 유무")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("할일 제목"),
                                        fieldWithPath("todoOrder").type(JsonFieldType.STRING).description("할일 순서"),
                                        fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("할일 성공 유무")
                                )));
    }

    default void getDocument(ResultActions resultActions) throws Exception {

        resultActions
                .andDo(
                        document(
                                "get-Todo",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("id").description("식별자")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("할일 제목"),
                                        fieldWithPath("todoOrder").type(JsonFieldType.STRING).description("할일 순서"),
                                        fieldWithPath("completed").type(JsonFieldType.BOOLEAN).description("할일 성공 유무")
                                )));
    }

    default void getPageOfDocuments(ResultActions resultActions) throws Exception {

        resultActions
                .andDo(
                        document(
                                "gets-Todo",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestParameters(
                                        parameterWithName("page").description("The page number"),
                                        parameterWithName("size").description("The number of items per page")
                                ),
                                responseFields(
                                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("배열"),
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("식별자"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING).description("할일 제목"),
                                        fieldWithPath("[].todoOrder").type(JsonFieldType.STRING).description("할일 순서"),
                                        fieldWithPath("[].completed").type(JsonFieldType.BOOLEAN).description("할일 성공 유무")
//                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("PageInfo"),
//                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("Page"),
//                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("Size"),
//                                        fieldWithPath("pageInfo.getTotalPages").type(JsonFieldType.NUMBER).description("getTotalPages"),
//                                        fieldWithPath("pageInfo.getTotalElements").type(JsonFieldType.NUMBER).description("getTotalElements")
                                )));
    }

    default void deleteDocument(ResultActions resultActions) throws Exception {

        resultActions
                .andDo(
                        document(
                                "delete-Todo",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("id").description("식별자")
                                )));
    }

}
