package com.Todo.TodoApplication.Todo.dto;

import com.Todo.TodoApplication.Todo.page.PageInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// Page 정보를 불러올때 사용됨
@Getter
@NoArgsConstructor
public class MultiResponseDto<T> {

    private List<T> data;

    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = PageInfo.builder()
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .getTotalPages(page.getTotalPages())
                .getTotalElements(page.getTotalElements())
                .build();
    }
}
