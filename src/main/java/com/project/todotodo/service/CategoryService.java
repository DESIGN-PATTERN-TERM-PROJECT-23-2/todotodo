package com.project.todotodo.service;

import com.project.todotodo.domain.CategoryDomain;
import com.project.todotodo.dto.Goal.GoalForm;
import com.project.todotodo.model.Category;
import com.project.todotodo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDomain> getCategorys(String name) {
        List<CategoryDomain> categorys = categoryRepository.findAll();
        return categorys;
    }
}