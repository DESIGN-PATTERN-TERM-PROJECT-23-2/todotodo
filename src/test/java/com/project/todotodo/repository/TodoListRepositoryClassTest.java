package com.project.todotodo.repository;

import com.project.todotodo.model.Node;
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
    void removeTodolistTest() {
        // Given
        Node toDoList = new ToDoList();
        toDoList.setNodeId(1L);

        // When
        todoListRepositoryClass.removeTodolist(toDoList);

        // Then
        // Add assertions to check if the removal is done as expected
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM todo_lists WHERE node_id = ?"), eq(1L));
        verify(jdbcTemplate, times(1)).update(eq("SELECT node_list_id FROM nodes WHERE parent_id = ?"), eq(1L));
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM nodes WHERE node_id = ?"), eq(1L));
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM node_lists WHERE node_list_id = ?"), anyLong());
        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM node_array_list WHERE node_id = ?"), eq(1L));
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

        // 생성이 예상대로 이루어졌는지 확인하는 추가적인 assert 구문을 추가하세요.

        // longValue()를 호출하기 전에 null 체크를 추가합니다.
        Long nodeId = ids.get(1);
        assertNotNull(nodeId, "Node ID가 null이 아니어야 합니다");
        assertEquals(1L, nodeId.longValue(), "Node ID는 1L이어야 합니다");

        Long todoId = ids.get(1);
        assertNotNull(todoId, "Todo ID는 null이 아니어야 합니다");
        assertEquals(1L, todoId.longValue(), "Todo ID는 1L이어야 합니다");
    }
}