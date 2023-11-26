package com.project.todotodo.service;

import com.project.todotodo.repository.TodoListRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;


    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    // create할 때 잊지 말고 parent id로 가서 list 레포 행동하기
}
