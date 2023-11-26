package com.project.todotodo.repository;

import com.project.todotodo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeListRepositoryClassTest {

    private NodeList nodeList;

    @BeforeEach
    void setUp() {
        Node root = new Root();
        this.nodeList = new NodeList(root, null);
        NodeListIterator iterator = this.nodeList.createIterator();

        Category category = new Category();
        category.setNodeId(1L);
        category.setContent("Root");
        category.setLevel(1);
        category.setNodeList(root);
        iterator.add(category);

        ToDoList toDoList = new ToDoList();
        toDoList.setNodeId(2L);
        toDoList.setContent("TestCategory");
        toDoList.setLevel(2);
        toDoList.setNodeList(category);
        iterator.add(toDoList);

    }

    @Test
    void iteratorShouldTraverseNodesCorrectly() {
        NodeListIterator iterator = this.nodeList.createIterator();

        // 최상위 노드로 이동
        assertTrue(iterator.hasNext());
        assertEquals("Root", iterator.next().getContent());

        // Category 노드로 이동
        assertTrue(iterator.hasNext());
        assertEquals("TestCategory", iterator.next().getContent());

        // 하위 ToDoList 노드로 이동
        assertTrue(iterator.hasNext());
        assertEquals("TestCategory", iterator.next().getContent());

        // 마지막 노드까지 순회
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorShouldRemoveNodesCorrectly() {
        NodeListIterator iterator = this.nodeList.createIterator();

        // Root 노드 다음에 Category 노드 추가
        assertTrue(iterator.hasNext());
        Node categoryNode = iterator.next();
        assertEquals("Root", categoryNode.getContent());

        assertTrue(iterator.hasNext());
        Node toDoListNode = iterator.next();
        assertEquals("TestCategory", toDoListNode.getContent());

        // Iterator를 사용하여 Category 노드 삭제
        iterator.remove();

        // 삭제 후에 다시 순회
        iterator = this.nodeList.createIterator();

        assertTrue(iterator.hasNext());
        assertEquals("Root", iterator.next().getContent());

        assertFalse(iterator.hasNext()); // Category 노드가 삭제되었으므로 다음 노드는 없어야 함
    }
}