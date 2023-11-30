package com.project.todotodo.dto.TodoList;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryList {
    private Long NodeId;
    private Long CategoryId;
    private String content;
    private List<TodoListElement> todoListElementList;

}
