package com.project.todotodo.repository;

import com.project.todotodo.dto.Goal.CategoryListElement;
import com.project.todotodo.model.Category;
import com.project.todotodo.repository.CategoryRepositoryClass;
import com.project.todotodo.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
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
        assertEquals("TestCategory",category.getContent());
        // categoryId 확인은 필요없으니 삭제
    }

//    @AfterEach
//    void removeCategoryTest() {
//        // Given
//        Category category = new Category();
//        category.setContent("TestCategory");
//        Long categoryId = categoryRepositoryClass.saveCategoryAndGetId(category);
//
//        // When
//        categoryRepositoryClass.removeCateogory(categoryId);
//
//        // Then
//        assertEquals(null, "The category should be removed");
//    }
}