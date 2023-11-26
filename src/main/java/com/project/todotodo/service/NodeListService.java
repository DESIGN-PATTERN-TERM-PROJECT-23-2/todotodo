package com.project.todotodo.service;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeList;
import com.project.todotodo.model.NodeListIterator;
import com.project.todotodo.repository.NodeListRepository;
import com.project.todotodo.repository.NodeListRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NodeListService {
    private final NodeListRepository nodeListRepository;
    private NodeList nodeList;
    private NodeListIterator nodeListIterator;

    private final NodeListRepositoryClass nodeListRepositoryClass;
    @Autowired
    public NodeListService(NodeListRepository nodeListRepository, NodeListRepositoryClass nodeListRepositoryClass) {
        this.nodeListRepository = nodeListRepository;
        this.nodeListRepositoryClass = nodeListRepositoryClass;
        this.nodeList = initNodeList();
    }

    private NodeList initNodeList(){
        NodeList nodeList = new NodeList();
        this.nodeListIterator = this.nodeList.createIterator();
        for (Node element : nodeListRepositoryClass.findCategories()) {
            nodeListIterator.add(element);
        }
        while(nodeListIterator.hasNext()){
            // ArrayList<Node>
        }
        // 이제 iterator로 순회하기...
        return nodeList;
    }
}
