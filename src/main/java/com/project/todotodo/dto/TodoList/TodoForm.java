package com.project.todotodo.dto.TodoList;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoForm {
    private Long ParentId;
    private String content;
    private int year;
    private int month;
    private int day;
}
