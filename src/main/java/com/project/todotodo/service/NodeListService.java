package com.project.todotodo.service;

import com.project.todotodo.repository.NodeListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeListService {
    private final NodeListRepository nodeListRepository;
}
