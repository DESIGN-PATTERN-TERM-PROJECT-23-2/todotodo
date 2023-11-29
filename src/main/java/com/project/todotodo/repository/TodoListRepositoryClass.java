package com.project.todotodo.repository;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @Transactional
    public ArrayList<Long> create(Node parent, ToDoList toDoList){
        ArrayList<Long> ids = new ArrayList<>(); // node_id, todo_list_id

        // 0. NodeList 만들기 (parentId 이용)
        // 1. Node 만들기 (NodeList의 nodelistid 이용)   -> node_id 반환값
        // 2. Node 만들 때 쓴 node id로 todolist 저장하기  - todo_list_id 반환값
        // 3. nodearraylist 만들기 (nodeid, parrent node의 nodelistid 이용)

        String sql1 = "INSERT INTO node_lists (parent_id) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, parent.getNodeId());
            return ps;
        }, keyHolder);
        Long node_list_id = keyHolder.getKey().longValue();

        String sql2 = "INSERT INTO nodes (content, is_category, level, node_list_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder1 = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, toDoList.getContent());
            ps.setInt(2, 0);
            ps.setInt(3, toDoList.getLevel());
            ps.setLong(4, node_list_id);
            return ps;
        }, keyHolder1);
        Long node_id = keyHolder1.getKey().longValue();

        String sql3 = "INSERT INTO todo_lists (date, is_complete, node_id, parent_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder2 = new GeneratedKeyHolder();
        LocalDateTime localDateTime = toDoList.getDate();
        Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, sqlDate);
            ps.setBoolean(2, false);
            ps.setLong(3, node_id);
            ps.setLong(4, parent.getNodeId());
            return ps;
        }, keyHolder2);
        Long todo_id = keyHolder2.getKey().longValue();

        String sql4 = "INSERT INTO node_array_list (node_id, node_list_id) VALUES (?, ?)";
        KeyHolder nodeArrayListId = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql4, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, parent.getNodeId());
            ps.setLong(2, node_list_id);
            return ps;
        }, nodeArrayListId);

        ids.add(node_id);
        ids.add(todo_id);
        return ids;
    }

}
