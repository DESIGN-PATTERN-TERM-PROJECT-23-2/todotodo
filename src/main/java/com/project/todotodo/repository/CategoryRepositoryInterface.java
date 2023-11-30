package com.project.todotodo.repository;

import com.project.todotodo.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryInterface {

    public Long saveCategoryAndGetId(Category category);

    public void removeCateogory(Long id);
}
