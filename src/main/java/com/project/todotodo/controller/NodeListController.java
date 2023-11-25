package com.project.todotodo.controller;

import com.project.todotodo.repository.NodeListRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping( value = "/nodeLists",  produces = "application/json;charset=utf8")
public class NodeListController {
    private final NodeListRepository nodeListRepository;

    public NodeListController(NodeListRepository nodeListRepository) {
        this.nodeListRepository = nodeListRepository;
    }
}
