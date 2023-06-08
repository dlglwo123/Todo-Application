package com.Todo.TodoApplication.helper;

import java.net.URI;

public interface TodoControllerTestHelper extends ControllerTestHelper{

    String DEFAULT_URI = "/todos";
    String RESOURCE_URI = "/{id}";

    default URI getUri(){
        return createUri(DEFAULT_URI);
    }
    default URI getResourceUri(long id){
        return createUri(DEFAULT_URI + RESOURCE_URI,id);
    }
}
