package com.project.todotodo.repository;

import com.project.todotodo.model.Node;
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

    public void removeTodolist(Node toDoList){

        // 1. todolist 지우기
        // 2. nodeId로 node 지우기
        // 3. 지웠던 node의 nodelistId로 nodelist 지우기
        // 4. nodeId가 같은 모든 row의 nodearraylist 지우기

        String sql1 = "DELETE FROM todo_lists WHERE node_id = ?";
        jdbcTemplate.update(sql1, toDoList.getNodeId());

        String sql2 = "SELECT node_list_id FROM nodes WHERE node_id = ?";
        Long nodeListId = jdbcTemplate.queryForObject(sql2, Long.class, toDoList.getNodeId());
        String sql3 = "DELETE FROM nodes WHERE node_id = ?";
        jdbcTemplate.update(sql3, toDoList.getNodeId());

        if (nodeListId != null) {
            String sql4 = "DELETE FROM node_lists WHERE node_list_id = ?";
            jdbcTemplate.update(sql4, nodeListId);
        }

        String sql5 = "DELETE FROM node_array_list WHERE node_id = ?";
        jdbcTemplate.update(sql5, toDoList.getNodeId());
    }

    public Long create(Node parent, ToDoList toDoList){
        // 1.
        // 2.
        // 3.
        // 4.


        return 0L;
    }

}
