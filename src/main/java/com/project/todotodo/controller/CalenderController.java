package com.project.todotodo.controller;

import com.project.todotodo.service.NodeListService;
import com.project.todotodo.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping( value = "/calender",  produces = "application/json;charset=utf8")
public class CalenderController {
    private final TodoListService todoListService;
    private final NodeListService nodeListService;

    public CalenderController(TodoListService todoListService, NodeListService nodeListService) {
        this.todoListService = todoListService;
        this.nodeListService = nodeListService;
    }

    @GetMapping("/{year}/{month}")
    public String getTasksByMonth(
            @PathVariable int year,
            @PathVariable int month,
            Model model) {

        // CategoryList categoryList = nodelistService.getCategoryListByDate(date);
        // model.addAttribute("date", date);
        // model.addAttribute("categoryList", categoryList);
        return "calender/month"; // HTML 템플릿의 이름 (day.html)
    }

}
