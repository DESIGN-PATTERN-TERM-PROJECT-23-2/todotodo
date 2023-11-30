package com.project.todotodo.repository;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import com.project.todotodo.repository.TodoListRepositoryClass;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TodoListRepositoryClassTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TodoListRepositoryClass todoListRepositoryClass;

    @Test
    void removeTodolistTest() {
        // Given
        Node toDoList = new ToDoList();
        toDoList.setNodeId(1L);

        // When
        todoListRepositoryClass.removeTodolist(toDoList);

        // Then
        // Add assertions to check if the removal is done as expected
    }

    @Test
    void createTest() {
        // Given
        Node parent = new ToDoList();
        ToDoList toDoList = new ToDoList();
        toDoList.setContent("Test ToDo");
        toDoList.setLevel(1);
        toDoList.setDate(LocalDateTime.now());

        // Mocking jdbcTemplate
        when(jdbcTemplate.update(any(), any(), any(GeneratedKeyHolder.class))).thenReturn(1);

        // When
        ArrayList<Long> ids = todoListRepositoryClass.create(parent, toDoList);

        // Then
        assertNotNull(ids);
        assertEquals(2, ids.size());
        // Add more assertions to check if the creation is done as expected
    }
}