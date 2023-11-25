package com.project.todotodo.controller;

import com.project.todotodo.dto.Goal.GoalForm;
import com.project.todotodo.model.Category;
import com.project.todotodo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/goal", produces = "application/json;charset=utf8")
public class GoalController {
    private final CategoryService categoryService;

    public GoalController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/create")
    public String createForm() {
        return "goal/create";
    }


    @PostMapping("/create")
    public String create(GoalForm goalForm){
        Category category = new Category();
        // goal form으로 category 저장하는 코드
        System.out.println(goalForm.getName());
        return "redirect:/";
    }


}
