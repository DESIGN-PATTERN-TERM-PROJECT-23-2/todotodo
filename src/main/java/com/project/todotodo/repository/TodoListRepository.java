package com.project.todotodo.repository;

import com.project.todotodo.domain.TodoListDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoListDomain, Long> {
}
