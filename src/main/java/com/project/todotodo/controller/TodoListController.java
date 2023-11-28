package com.project.todotodo.controller;

import com.project.todotodo.domain.NodeListDomain;
import com.project.todotodo.dto.TodoList.CategoryList;
import com.project.todotodo.service.NodeListService;
import org.springframework.ui.Model;

import com.project.todotodo.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

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

        LocalDate date = LocalDate.of(year, month, day);
        CategoryList categoryList = nodeListService.getCategoryListByDate(date);
        model.addAttribute("date", date);
        model.addAttribute("categoryList", categoryList);
        return "todolist/day"; // HTML 템플릿의 이름 (day.html)
    }

}
