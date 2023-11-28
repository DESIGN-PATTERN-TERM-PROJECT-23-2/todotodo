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


    public ArrayList<Node> findCategories(Node root) {
        List<Category> categoryList = jdbcTemplate.query(
                "SELECT * FROM nodes INNER JOIN categories ON nodes.node_id WHERE level = 0",
                (resultSet, rowNum) -> {
                    Category category = new Category();
                    category.setNodeList(root);
                    category.setLevel(resultSet.getInt("level"));
                    category.setContent(resultSet.getString("content"));
                    category.setNodeId(resultSet.getLong("node_id"));
                    category.setCategoryId(resultSet.getLong("category_id"));
                    return category;
                });
        /*
        List<Category> categoryList = jdbcTemplate.query(
                "SELECT * FROM nodes WHERE level = 0",
                (resultSet, rowNum) -> {
                    Category category = new Category();
                    category.setNodeList(root);
                    category.setLevel(resultSet.getInt("level"));
                    category.setContent(resultSet.getString("content"));
                    category.setNodeId(resultSet.getLong("node_id"));
                    return category;
                });
         */

        return new ArrayList<>(categoryList);
    }

    public ArrayList<Node> findByParentId(Long id, Node parent) {
        List<Long> nodeIdList = getNodeIdsByParentId(id);
        ArrayList nodes = new ArrayList<Node>();
        for (Long element : nodeIdList){
            ToDoList todo = getTodoListById(element, parent);
            nodes.add(todo);
        }
        return nodes;
    }

    public List<Long> getNodeIdsByParentId(Long parentId) {
        String sql = "SELECT node_id FROM todo_lists WHERE parent_id = ?";
        return jdbcTemplate.queryForList(sql, Long.class, parentId);
    }

    public ToDoList getTodoListById(Long nodeId, Node parent) {
        ToDoList toDoList = jdbcTemplate.queryForObject(
                "SELECT n.*, tl.* FROM nodes n JOIN todo_lists tl ON n.node_id = tl.node_id WHERE n.node_id = ?",
                (resultSet, rowNum) -> {
                    ToDoList todo = new ToDoList();
                    todo.setNodeList(parent);
                    todo.setLevel(resultSet.getInt("n.level"));
                    todo.setContent(resultSet.getString("n.content"));
                    todo.setNodeId(resultSet.getLong("n.node_id"));
                    todo.setParent(parent);
                    todo.setComplete(resultSet.getInt("tl.is_complete") == 1);
                    Timestamp timestamp = resultSet.getTimestamp("tl.date");
                    if (timestamp != null) {
                        todo.setDate(timestamp.toLocalDateTime());
                    }
                    return todo;
                },
                nodeId);

        return toDoList;
    }


}
