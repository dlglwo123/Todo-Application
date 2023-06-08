package com.Todo.TodoApplication.helper;

import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public interface ControllerTestHelper <T>{

    default RequestBuilder postRequestBuilder(URI uri,
                                              String content){
        return MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
    }

    default MockHttpServletRequestBuilder patchRequestBuilder(String content,
                                                              long Id){

        return RestDocumentationRequestBuilders
                .patch("/todos/{id}",Id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
    }

    default MockHttpServletRequestBuilder getRequestBuilder(long Id){

        return RestDocumentationRequestBuilders
                .get("/todos/{id}",Id)
                .accept(MediaType.APPLICATION_JSON);
    }

    default MockHttpServletRequestBuilder getListOfRequestBuilder(URI uri,
                                                   MultiValueMap<String,String> params){

        return RestDocumentationRequestBuilders
                .get(uri)
                .params(params)
                .accept(MediaType.APPLICATION_JSON);
    }

    default MockHttpServletRequestBuilder deleteRequestBuilder(long Id){

        return RestDocumentationRequestBuilders
                .delete("/todos/{id}",Id)
                .accept(MediaType.APPLICATION_JSON);
    }

    default URI createUri(String url){

        return UriComponentsBuilder
                .newInstance()
                .path(url)
                .build()
                .toUri();
    }

    default URI createUri(String url,long id){

        return UriComponentsBuilder
                .newInstance()
                .path(url)
                .buildAndExpand(id)
                .toUri();
    }
}
