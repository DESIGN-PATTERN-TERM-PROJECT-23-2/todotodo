package com.project.todotodo.service;

import com.project.todotodo.dto.TodoList.CategoryList;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeList;
import com.project.todotodo.model.NodeListIterator;
import com.project.todotodo.model.Root;
import com.project.todotodo.repository.NodeListRepository;
import com.project.todotodo.repository.NodeListRepositoryClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        System.out.println("created NodeListService-------");
    }

    private NodeList initNodeList(){
        Node root = new Root();
        // NodeList nodeList = new NodeList(root, null);
        this.nodeList = new NodeList(root, null);
        this.nodeListIterator = this.nodeList.createIterator();
        for (Node element : nodeListRepositoryClass.findCategories(root)) {
            nodeListIterator.add(element);
            System.out.println("added category -----");
        }

        while(nodeListIterator.hasNext()){
            // ArrayList<Node>
            Node parent = nodeListIterator.next();
            Node savedCurr = nodeListIterator.getCurr();
            nodeListIterator.setCurr(parent);
            ArrayList<Node> children = nodeListRepositoryClass.findByParentId(parent.getNodeId(), parent);
            if(children != null) {
                for (Node element : children) {
                    nodeListIterator.add(element);
                }
            }
            nodeListIterator.setCurr(savedCurr);
        }
        nodeListIterator.initCurr();
        // 이제 iterator로 순회하기...
        return nodeList;
    }

    public NodeListIterator getIterator(){
        return this.nodeListIterator;
    }

    public CategoryList getCategoryListByDate(LocalDate date){
        CategoryList categoryList = new CategoryList();
        // to be updated
        return categoryList;
    }

}
