package com.project.todotodo.controller;

import com.project.todotodo.service.TodoListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping( value = "/goal",  produces = "application/json;charset=utf8")
public class GoalController {
    //private final TodoListService todoListService;

//    public TodoListController(TodoListService todoListService) {
//        this.todoListService = todoListService;
//    }
}
