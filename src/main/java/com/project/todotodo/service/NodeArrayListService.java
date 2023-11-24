package com.project.todotodo.service;

import com.project.todotodo.repository.NodeArrayListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeArrayListService {
    private final NodeArrayListRepository nodeArrayListRepository;
}
