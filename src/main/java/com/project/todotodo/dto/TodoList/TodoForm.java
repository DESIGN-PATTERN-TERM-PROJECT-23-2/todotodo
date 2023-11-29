package com.project.todotodo.dto.TodoList;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoForm {
    private String ParentId;
    private String content;
    private String year;
    private String month;
    private String day;
}
