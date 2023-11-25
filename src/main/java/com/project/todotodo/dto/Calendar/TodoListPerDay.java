package com.project.todotodo.dto.Calendar;

import com.project.todotodo.dto.TodoList.TodoListElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoListPerDay {
    private int day;
    private List<TodoListElement> todoListOnDayListOnDay;
}
