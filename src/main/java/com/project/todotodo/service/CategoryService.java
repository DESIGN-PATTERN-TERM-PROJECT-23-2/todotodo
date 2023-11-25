package com.project.todotodo.service;

import com.project.todotodo.domain.CategoryDomain;
import com.project.todotodo.domain.NodeDomain;
import com.project.todotodo.dto.Goal.GoalForm;

import com.project.todotodo.repository.CategoryRepository;
import com.project.todotodo.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDomain> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(String name) {
        CategoryDomain newCategory = new CategoryDomain();
        NodeDomain node = new NodeDomain();

        node.setContent(name);
        newCategory.setNode(node);

        categoryRepository.save(newCategory);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}