package com.project.todotodo.controller;

import com.project.todotodo.domain.NodeListDomain;
import com.project.todotodo.dto.Goal.GoalForm;
import com.project.todotodo.dto.TodoList.CategoryList;
import com.project.todotodo.dto.TodoList.TodoForm;
import com.project.todotodo.service.NodeListService;
import org.springframework.ui.Model;

import com.project.todotodo.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping( value = "/todolist",  produces = "application/json;charset=utf8")
public class TodoListController {
    private final TodoListService todoListService;
    private final NodeListService nodeListService;

    public TodoListController(TodoListService todoListService, NodeListService nodeListService) {
        this.todoListService = todoListService;
        this.nodeListService = nodeListService;
    }

    @GetMapping("/{year}/{month}/{day}")
    public String getTasksByDate(
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int day,
            Model model) {

        //LocalDate date = LocalDate.of(year.intValue(), month.intValue(), day.intValue());
        LocalDate date = LocalDate.of(year, month, day);
        List<CategoryList> categoryLists = nodeListService.getCategoryListByDate(date);
        model.addAttribute("date", date);
        model.addAttribute("categoryLists", categoryLists);
        model.addAttribute("tyear", year);
        model.addAttribute("tmonth", month);
        model.addAttribute("tday", day);

        return "todolist/day"; // HTML 템플릿의 이름 (day.html)
    }

    @PostMapping("/create/{year}/{month}/{day}/{nodeId}")
    public String createTodo(
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int day,
            @PathVariable Long nodeId,
            Model model){
        model.addAttribute("year", String.valueOf(year));
        model.addAttribute("month", String.valueOf(month));
        model.addAttribute("day", String.valueOf(day));
        model.addAttribute("parentId", String.valueOf(nodeId));
        return "todolist/create";  // HTML 템플릿의 이름 (create.html)
    }

    @PostMapping("/create")
    public String create(TodoForm todoForm) throws IOException {
        // 월 값 확인 및 조정
        //int month = Math.max(1, Math.min(12, todoForm.getMonth()));
        // 일 값 확인 및 조정
        //int day = Math.max(1, Math.min(31, todoForm.getDay()));
        Long parentId = Long.valueOf(todoForm.getParentId());
        int year = Integer.parseInt(todoForm.getYear());
        int month = Integer.parseInt(todoForm.getMonth());
        int day = Integer.parseInt(todoForm.getDay());

        LocalDateTime dateTime = LocalDateTime.of(
                year, month, day,
                0, 0, 0);
        todoListService.createTodoList(parentId, todoForm.getContent(), dateTime);
        System.out.println(todoForm.getContent());
        return "redirect:/todolist/"
                + String.valueOf(todoForm.getYear())
                +"/"+String.valueOf(month)  // 수정된 부분
                +"/"+String.valueOf(day)    // 수정된 부분
                ;
    }
}
