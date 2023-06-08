package com.Todo.TodoApplication.Todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TodoDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{

        private String title;

        private String todoOrder;

        private boolean completed;
    }
    @Getter
    @NoArgsConstructor
    @Setter
    @AllArgsConstructor
    public static class Patch{

        private long id;

        private String title;

        private String todoOrder;

        private boolean completed;

    }
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private long id;

        private String title;

        private String todoOrder;

        private boolean completed;
    }
}
