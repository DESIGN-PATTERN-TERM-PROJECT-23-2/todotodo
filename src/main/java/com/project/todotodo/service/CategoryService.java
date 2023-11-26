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
    private final NodeRepository nodeRepository;

    public List<CategoryDomain> getAllCategories() {
        return categoryRepository.findAll();
    }

    public GoalForm createCategory(String name) {
        CategoryDomain newCategory = new CategoryDomain();
        NodeDomain node = new NodeDomain();

<<<<<<< HEAD
        node.setContent(name);
        newCategory.setNode(node);

        CategoryDomain savedCategory = categoryRepository.save(newCategory);

        GoalForm goalForm = new GoalForm();
        goalForm.setName(savedCategory.getNode().getContent());

        return goalForm;
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);

=======
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
        nodeListIterator.remove(id);
        // categoryRepositoryClass.remove(id);
        return;
>>>>>>> b05fa7d (merge 전 커밋)
    }
}