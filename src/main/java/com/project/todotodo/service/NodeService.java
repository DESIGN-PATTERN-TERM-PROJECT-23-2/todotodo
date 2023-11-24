package com.project.todotodo.service;

import com.project.todotodo.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeService {
    private final NodeRepository nodeRepository;
}
