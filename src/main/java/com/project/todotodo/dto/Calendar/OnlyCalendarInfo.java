package com.project.todotodo.dto.Calendar;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OnlyCalendarInfo {
    private int year;
    private int month;

    private List<TodoListPerDay> todoListPerDayList;

}
