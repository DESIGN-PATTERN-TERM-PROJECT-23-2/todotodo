package com.project.todotodo.model;

import com.project.todotodo.repository.NodeListRepositoryClass;
import com.project.todotodo.service.NodeListService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class NodeListIteratorTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NodeList nodeList;
    private NodeListIterator nodeListIterator;

    @BeforeEach
    public void setup(){
        Node root = new Root();
        // NodeList nodeList = new NodeList(root, null);
        this.nodeList = new NodeList(root, null);
        this.nodeListIterator = nodeList.createIterator();
        NodeListRepositoryClass nodeListRepositoryClass = new NodeListRepositoryClass(this.jdbcTemplate);
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
    }


    @Test
    void getCategoryList() {

    }

    @Test
    void getAllChildrenWithDFS() {
        Node root = nodeListIterator.getRoot();
        ArrayList<Node> all = nodeListIterator.getAllChildrenWithDFS(root);
        System.out.println("dfs done");
    }

    @Test
    void getAllChildrenWithBFS() {
    }

    @Test
    void getIndexAmongChildren() {
    }

    @Test
    void findNodeInRoot() {
    }

    @Test
    void addToGivenParent() {
    }
}