package com.project.todotodo.service;

import com.project.todotodo.dto.CategoryCreateRes;
import com.project.todotodo.model.Category;
import com.project.todotodo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategorys(String name) {
        List<Category> categorys = categoryRepository.findByname(name);
        return getCategorys(name);
    }

    public CategoryCreateRes createCategory(String name){
        Category newCategory = new Category();
        CategoryCreateRes categoryCreateRes = new CategoryCreateRes();

        newCategory.setName(name);

        return categoryCreateRes;
    }

    public void deleteCategory(String name) {
        categoryRepository.deleteByName(name);
    }
}
