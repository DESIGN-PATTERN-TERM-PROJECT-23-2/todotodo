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

import javax.annotation.PostConstruct;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class NodeListIteratorTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    static private JdbcTemplate sJdbcTemplate;


    static private NodeList nodeList;
    static private NodeListIterator nodeListIterator;


    public NodeListIteratorTest(){
        this.sJdbcTemplate = jdbcTemplate;
        System.out.println("post construct ------");
    }

    @BeforeEach
    public void setup(){
        Node root = new Root();
        // NodeList nodeList = new NodeList(root, null);
        nodeList = new NodeList(root, null);
        nodeListIterator = nodeList.createIterator();
        NodeListRepositoryClass nodeListRepositoryClass = new NodeListRepositoryClass(jdbcTemplate);
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
    void getAllChildrenWithDFS() {
        Node root = nodeListIterator.getRoot();
        ArrayList<Node> all = nodeListIterator.getAllChildrenWithDFS(root);
        ArrayList<Node> allBFS = nodeListIterator.getAllChildrenWithBFS(root);
        System.out.println("dfs done");
        while(nodeListIterator.hasNext()){
            Node node = nodeListIterator.next();
            System.out.println(node.getNodeId());
        }
        System.out.println("next done");
        nodeListIterator.initCurr();
        nodeListIterator.next();
        Node pre = nodeListIterator.previous();
        nodeListIterator.next();
        //System.out.println(pre.getNodeId()); // error here b.c. it
        Node parent = nodeListIterator.getParent();
        System.out.println(parent.getNodeId());
        ArrayList<Node> children = nodeListIterator.getChildren();
        ArrayList<Node> categories = nodeListIterator.getCategoryList();
        ArrayList<Node> siblings = nodeListIterator.getSibling();
        System.out.println("test done----------");
    }


    // remove error1!!
    // findNodeInRoot error!!!!
}