package com.project.todotodo.service;

import com.project.todotodo.domain.CategoryDomain;
import com.project.todotodo.domain.NodeDomain;
import com.project.todotodo.dto.Goal.CategoryListElement;

import com.project.todotodo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryListElement> getAllCategories() {
        List<CategoryDomain> categoryDomains = categoryRepository.findAll();

        List<CategoryListElement> categoryListElements = categoryDomains.stream()
                .map(this::convertToCategoryListElement)
                .collect(Collectors.toList());

        return categoryListElements;
    }

    private CategoryListElement convertToCategoryListElement(CategoryDomain categoryDomain) {
        CategoryListElement categoryListElement = new CategoryListElement();
        categoryListElement.setNodeId(categoryDomain.getNode().getNodeId());
        categoryListElement.setCategoryId(categoryDomain.getCategoryId());
        categoryListElement.setContent(categoryDomain.getNode().getContent());
        return categoryListElement;
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