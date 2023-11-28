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
            @PathVariable Long year,
            @PathVariable Long month,
            @PathVariable Long day,
            Model model) {

        LocalDate date = LocalDate.of(year.intValue(), month.intValue(), day.intValue());
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
            @PathVariable Long year,
            @PathVariable Long month,
            @PathVariable Long day,
            @PathVariable Long nodeId,
            Model model){
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("parentId", nodeId);
        return "todolist/create";  // HTML 템플릿의 이름 (create.html)
    }

    @PostMapping("/create")
    public String create(TodoForm todoForm){
        LocalDateTime dateTime = LocalDateTime.of(
                todoForm.getYear(), todoForm.getMonth(), todoForm.getDay(),
                0, 0, 0);
        todoListService.createTodoList(todoForm.getParentId(), todoForm.getContent(), dateTime);
        System.out.println(todoForm.getContent());
        return "redirect:/todolist/"
                + String.valueOf(todoForm.getYear())
                +"/"+String.valueOf(todoForm.getMonth())
                +"/"+String.valueOf(todoForm.getDay());
    }

}
