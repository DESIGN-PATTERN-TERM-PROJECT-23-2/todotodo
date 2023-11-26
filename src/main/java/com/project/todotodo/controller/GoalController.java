package com.project.todotodo.controller;

import com.project.todotodo.dto.Goal.CategoryListElement;
import org.springframework.ui.Model;
import com.project.todotodo.domain.CategoryDomain;
import com.project.todotodo.dto.Goal.GoalForm;
import com.project.todotodo.model.Category;
import com.project.todotodo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public String list(Model model){
        List<CategoryListElement> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "goal/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/goal/list"; // 삭제 후 카테고리 목록으로 리다이렉트
    }


}
