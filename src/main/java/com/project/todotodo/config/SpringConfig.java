package com.project.todotodo.config;

import com.project.todotodo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final CategoryRepository categoryRepository;
    private final NodeArrayListRepository nodeArrayListRepository;
    private final NodeRepository nodeDomainRepository;
    private final NodeListRepository nodeListRepository;
    private final TodoListRepository todoListRepository;
    @Autowired
    public SpringConfig(CategoryRepository categoryRepository, NodeArrayListRepository nodeArrayListRepository, NodeRepository nodeDomainRepository, NodeListRepository nodeListRepository, TodoListRepository todoListRepository){
        this.categoryRepository = categoryRepository;
        this.nodeArrayListRepository = nodeArrayListRepository;
        this.nodeDomainRepository = nodeDomainRepository;
        this.nodeListRepository = nodeListRepository;
        this.todoListRepository = todoListRepository;
    }
}
