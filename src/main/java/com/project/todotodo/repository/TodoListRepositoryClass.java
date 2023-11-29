package com.project.todotodo.repository;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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

        String sql2 = "SELECT node_list_id FROM nodes WHERE parent_id = ?";
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

    public ArrayList<Long> create(Node parent, ToDoList toDoList){
        ArrayList<Long> ids = new ArrayList<>(); // node_id, todo_list_id
        // 0. nodelist 만들기
        // 1. Node를 만들기 (nodelistid
        // 2. Node 만들 때 쓴 node id로 todolist 저장하기
        // 3.
        // 4.

        // parent child 달아주기 (array list)


        return ids;
    }

}