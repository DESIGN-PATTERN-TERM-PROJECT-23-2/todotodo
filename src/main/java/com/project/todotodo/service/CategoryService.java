package com.project.todotodo.service;

import com.project.todotodo.domain.CategoryDomain;
import com.project.todotodo.domain.NodeDomain;
import com.project.todotodo.dto.Goal.CategoryListElement;
import com.project.todotodo.dto.Goal.GoalForm;

import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeList;
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

    public Long createCategory(Long parentId, String name) {
        Category category = new Category();
        category.setContent(name);
        category.setLevel(0);
        category.setNodeList(new NodeList());



        nodeListIterator.addToGivenParent(parentId, category);



        return 0L;
    }

    public void deleteCategoryById(Long id) {
        // nodeListIterator.remove(id);
    }
}