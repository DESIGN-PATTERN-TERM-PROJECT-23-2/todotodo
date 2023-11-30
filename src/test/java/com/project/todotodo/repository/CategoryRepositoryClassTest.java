package com.project.todotodo.repository;

import com.project.todotodo.dto.Goal.CategoryListElement;
import com.project.todotodo.model.Category;
import com.project.todotodo.repository.CategoryRepositoryClass;
import com.project.todotodo.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CategoryRepositoryClassTest {

    @Autowired
    private CategoryRepositoryClass categoryRepositoryClass;

    @Autowired
    private CategoryService categoryService;

    @Test
    void saveCategoryAndGetIdTest() {
        // Given
        Category category = new Category();
        category.setContent("TestCategory");

        // When
        Long categoryId = categoryRepositoryClass.saveCategoryAndGetId(category);

        // Then
        assertNotNull(categoryId);
    }

    @Test
    void removeCategoryTest() {
        // Given
        Category category = new Category();
        category.setContent("TestCategory");
        Long categoryId = categoryRepositoryClass.saveCategoryAndGetId(category);

        // When
        categoryRepositoryClass.removeCateogory(categoryId);

        // Then
        // Add assertions to check if the category is removed as expected
    }

    @Test
    void createCategoryTest() {
        // Given
        String categoryName = "TestCategory";

        // When
        Long categoryId = categoryService.createCategory(categoryName);

        // Then
        assertNotNull(categoryId);
    }

    @Test
    void deleteCategoryByIdTest() {
        // Given
        Category category = new Category();
        category.setContent("TestCategory");
        Long categoryId = categoryRepositoryClass.saveCategoryAndGetId(category);

        // When
        categoryService.deleteCategoryById(categoryId);

        // Then
        // Add assertions to check if the category is deleted as expected
    }

    @Test
    void getAllCategoriesTest() {
        // When
        // Call the method to get all categories
        List<CategoryListElement> categories = categoryService.getAllCategories();

        // Then
        // Add assertions to check if the categories are retrieved as expected
    }
}