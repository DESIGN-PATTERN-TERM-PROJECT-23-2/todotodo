package com.project.todotodo.repository;

import com.project.todotodo.domain.CategoryDomain;
import com.project.todotodo.dto.Goal.CategoryListElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryDomain, Long> {

}
