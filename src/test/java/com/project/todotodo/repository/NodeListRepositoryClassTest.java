package com.project.todotodo.repository;

import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;  // 추가된 부분
import static org.mockito.Mockito.when;

public class NodeListRepositoryClassTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private NodeListRepositoryClass nodeListRepositoryClass;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCategories() {
        // Mock data
        Node root = new Node() {
            // Implement necessary methods for Node
        };
        root.setNodeId(1L);

        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setNodeList(root);
        category.setLevel(0);
        category.setContent("TestCategory");
        category.setNodeId(2L);
        category.setCategoryId(3L);
        categoryList.add(category);

        when(jdbcTemplate.query(eq("SELECT * FROM nodes INNER JOIN categories ON nodes.node_id = categories.node_id WHERE level = 0"),
                any(Object[].class), any(RowMapper.class))).thenReturn(categoryList);


        // Call the method
        ArrayList<Node> result = nodeListRepositoryClass.findCategories(root);

        // Assertions
        assertEquals(1, result.size());
        assertEquals("TestCategory", result.get(0).getContent());
        assertEquals(3L, ((Category) result.get(0)).getCategoryId());
    }

    @Test
    public void testGetTodoListById() {
        // Mock data
        Node parent = new Node() {
            // Implement necessary methods for Node
        };
        parent.setNodeId(1L);

        ToDoList expectedToDoList = new ToDoList();
        expectedToDoList.setNodeList(parent);
        expectedToDoList.setLevel(1);
        expectedToDoList.setContent("TestToDo");
        expectedToDoList.setNodeId(2L);
        expectedToDoList.setTodoListId(3L);
        expectedToDoList.setParent(parent);
        expectedToDoList.setComplete(true);
        expectedToDoList.setDate(LocalDateTime.now());

        // Mock jdbcTemplate.queryForObject
        when(jdbcTemplate.queryForObject(eq("SELECT n.*, tl.* FROM nodes n JOIN todo_lists tl ON n.node_id = tl.node_id WHERE n.node_id = ?"),
                any(), any(), eq(2L))).thenReturn(expectedToDoList);

        // Call the method
        ToDoList result = nodeListRepositoryClass.getTodoListById(2L, parent);

        // Assertions
        assertEquals("TestToDo", result.getContent());
        assertEquals(3L, result.getTodoListId());
        assertEquals(true, result.isComplete());
    }

    // Add more tests for other methods as needed
}