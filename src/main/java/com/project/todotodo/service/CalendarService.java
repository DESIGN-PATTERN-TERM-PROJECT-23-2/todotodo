package com.project.todotodo.service;

import com.project.todotodo.repository.NodeRepository;
import com.project.todotodo.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final TodoListRepository todoListRepository;
    private final NodeRepository nodeRepository;
}
