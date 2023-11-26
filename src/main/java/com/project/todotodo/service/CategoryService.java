package com.project.todotodo.service;

import com.project.todotodo.domain.CategoryDomain;
import com.project.todotodo.domain.NodeDomain;
import com.project.todotodo.dto.Goal.CategoryListElement;
import com.project.todotodo.dto.Goal.GoalForm;

import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeListIterator;
import com.project.todotodo.repository.CategoryRepository;
import com.project.todotodo.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final NodeListService nodeListService;
    private NodeListIterator nodeListIterator;

    public CategoryService(CategoryRepository categoryRepository, NodeListService nodeListService) {
        this.categoryRepository = categoryRepository;
        this.nodeListService = nodeListService;
        this.nodeListIterator = nodeListService.getIterator();
    }

    public List<CategoryListElement> getAllCategories() {
        List<Node> categoryList = nodeListIterator.getCategoryList();
        List<CategoryListElement> categoryDtoList = new ArrayList<>();
        for (Node category : categoryList) {
            CategoryListElement categoryDto = new CategoryListElement().ToDTO(category);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    public GoalForm createCategory(Long parentId, String name) {
        Category category = new Category();
        //category.


        CategoryDomain newCategory = new CategoryDomain();
        NodeDomain node = new NodeDomain();

        node.setContent(name);
        newCategory.setNode(node);

        CategoryDomain savedCategory = categoryRepository.save(newCategory);

        GoalForm goalForm = new GoalForm();
        goalForm.setName(savedCategory.getNode().getContent());

        return goalForm;
    }

    public void deleteCategoryById(Long id) {
        // nodeListIterator.remove(id);
    }
}