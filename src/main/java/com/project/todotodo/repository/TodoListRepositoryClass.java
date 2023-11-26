package com.project.todotodo.repository;

import com.project.todotodo.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TodoListRepositoryClass {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TodoListRepositoryClass(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void removeTodolist(ToDoList toDoList){

        // 1. todolist 지우기
        // 2. nodeId로 node 지우기
        // 3. node의 nodelistId로 nodelist 지우기
        // 4. nodeId로 nodearraylist 지우기

        String sql = "DELETE FROM categories WHERE node_id = ?";
        jdbcTemplate.update(sql, toDoList.getNodeId());
    }
}
