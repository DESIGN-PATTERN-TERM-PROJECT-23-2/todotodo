package com.project.todotodo.repository;

import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeList;
import com.project.todotodo.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NodeListRepositoryClass {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NodeListRepositoryClass (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public ArrayList<Node> findCategories() {
        List<Category> categoryList = jdbcTemplate.query(
                "SELECT * FROM nodes WHERE node_id = 0",
                (resultSet, rowNum) -> {
                    Category category = new Category();
                    category.setNodeList(new NodeList());
                    category.setLevel(resultSet.getInt("level"));
                    category.setContent(resultSet.getString("content"));
                    category.setNodeId(resultSet.getLong("node_id"));
                    return category;
                });

        return new ArrayList<>(categoryList);
    }

    public ArrayList<Node> findByParentId(Long id, Node parent) {
        List<Node> nodeList = jdbcTemplate.query(
                "SELECT * FROM todo_lists WHERE parent_id = ?",
                new Object[]{id},
                (resultSet, rowNum) -> {
                    ToDoList todo = new ToDoList();
                    todo.setNodeList(new NodeList());
                    todo.setLevel(resultSet.getInt("level"));
                    todo.setContent(resultSet.getString("content"));
                    todo.setNodeId(resultSet.getLong("node_id"));
                    todo.setParent(parent);
                    todo.setComplete(resultSet.getInt("is_complete") == 1);
                    Timestamp timestamp = resultSet.getTimestamp("date");
                    if (timestamp != null) {
                        todo.setDate(timestamp.toLocalDateTime());
                    }
                    return todo;
                });

        return new ArrayList<>(nodeList);
    }

}
