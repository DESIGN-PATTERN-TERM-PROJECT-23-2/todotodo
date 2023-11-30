package com.project.todotodo.repository;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeListIterator;
import com.project.todotodo.model.ToDoList;
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
    void createTest() {
        // Given
        Node parent = new ToDoList();   // parent
        ToDoList todo = new ToDoList(); // todolist
        todo.setContent("Test ToDo");
        todo.setLevel(1);
        todo.setDate(LocalDateTime.of(2023, 11, 25, 0, 0));

        // Mocking jdbcTemplate
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        when(jdbcTemplate.update(any(), any(), eq(keyHolder))).thenReturn(1);

        // When
        ArrayList<Long> ids = todoListRepositoryClass.create(parent, todo);

        // Then
        assertEquals(2, ids.size());

        Long nodeId = ids.get(0); // 첫 번째 요소는 Node ID
        assertNotNull(nodeId, "Node ID가 null이 아니어야 합니다");
        assertEquals(0L, nodeId.longValue());

        Long todoId = ids.get(1); // 두 번째 요소는 Todo ID
        assertNotNull(todoId, "Todo ID는 null이 아니어야 합니다");
        assertEquals(1L, todoId.longValue());
    }
}