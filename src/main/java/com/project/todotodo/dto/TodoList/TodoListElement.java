package com.project.todotodo.dto.TodoList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoListElement {
    private Long NodeId;
    private Long TodoListId;
    private int level;
    private String Content;
}
