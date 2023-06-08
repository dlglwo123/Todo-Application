package com.Todo.TodoApplication.Todo.page;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageInfo {

    private int page;
    private int size;
    private int getTotalPages;
    private long getTotalElements;
}
