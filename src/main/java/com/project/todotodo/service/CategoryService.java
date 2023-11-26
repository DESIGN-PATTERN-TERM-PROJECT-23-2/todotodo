package com.project.todotodo.service;

import com.project.todotodo.dto.Goal.CategoryListElement;

import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeList;
import com.project.todotodo.model.NodeListIterator;
import com.project.todotodo.repository.CategoryRepository;
import com.project.todotodo.repository.CategoryRepositoryClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final NodeListService nodeListService;

    private final TodoListService todoListService;
    private NodeListIterator nodeListIterator;

    private final CategoryRepositoryClass categoryRepositoryClass;

    public CategoryService(CategoryRepository categoryRepository, NodeListService nodeListService, TodoListService todoListService, CategoryRepositoryClass categoryRepositoryClass) {
        this.categoryRepository = categoryRepository;
        this.nodeListService = nodeListService;
        this.nodeListIterator = nodeListService.getIterator();
        this.todoListService = todoListService;
        this.categoryRepositoryClass = categoryRepositoryClass;
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
        // Parent id의 nodelist에
        Category category = new Category();
        category.setContent(name);
        category.setLevel(0);
        category.setNodeList(new NodeList());
        category.setNodeId(categoryRepositoryClass.saveCategoryAndGetId(category));
        nodeListIterator.addToGivenParent(parentId, category);
        return 0L;
    }

    public void deleteCategoryById(Long id) {
        ArrayList<Node> children = nodeListIterator.getAllChildrenWithBFS(nodeListIterator.findNodeInRoot(id));
        ListIterator<Node> iterator = children.listIterator(children.size());

        while (iterator.hasPrevious()) {
            Node currentNode = iterator.previous();
            todoListService.deleteTodoListById(currentNode.getNodeId());
        }
        nodeListIterator.remove(id);
        categoryRepositoryClass.removeCateogory(id);
        return;
    }
}