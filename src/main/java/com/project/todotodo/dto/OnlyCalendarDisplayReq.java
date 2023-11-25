package com.project.todotodo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@Schema(title = "연 월")
public class OnlyCalendarDisplayReq {
    @Schema(description = "년도", example = "2023")
    private int year;
    @Schema(description = "월", example = "11")
    private int month;
    /*@Schema(description = "각 일자별 할일", example = "")
    private ArrayList<String> todoList;
    */
}
