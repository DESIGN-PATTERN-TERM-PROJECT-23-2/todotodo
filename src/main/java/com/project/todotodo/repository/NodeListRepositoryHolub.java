package com.project.todotodo.repository;

import com.holub.database.*;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.DatagBaseSingleton;
import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NodeListRepositoryHolub implements NodeListRepositoryInterface {
    @Autowired
    public NodeListRepositoryHolub() {}

    public ArrayList<Node> findCategories(Node root) throws IOException, ParseFailure {
        List<Category> categoryList = new ArrayList<>();
        Table table = DatagBaseSingleton.getInstance().getDatabase().execute("SELECT * FROM nodes, categories WHERE nodes.node_id = categories.node_id AND nodes.level = 0");
        Cursor cursor = table.rows();
        for (int i = 0; cursor.advance(); i++) {
            Category category = new Category();
            category.setNodeList(root);
            category.setLevel(Integer.parseInt(cursor.column("level").toString()));
            category.setContent(cursor.column("content").toString());
            category.setNodeId(Long.parseLong(cursor.column("node_id").toString()));
            category.setCategoryId(Long.parseLong(cursor.column("category_id").toString()));
            categoryList.add(category);
        }

        return new ArrayList<>(categoryList);
    }

    public ArrayList<Node> findByParentId(Long id, Node parent) throws IOException, ParseFailure {
        List<Long> nodeIdList = getNodeIdsByParentId(id);
        ArrayList nodes = new ArrayList<Node>();
        for (Long element : nodeIdList){
            ToDoList todo = getTodoListById(element, parent);
            nodes.add(todo);
        }
        return nodes;
    }

    public List<Long> getNodeIdsByParentId(Long parentId) throws IOException, ParseFailure {
        List<Long> nodeIdList = new ArrayList<>();
        Table table = DatagBaseSingleton.getInstance().getDatabase().execute("SELECT node_id FROM todo_lists WHERE parent_id = "+parentId.toString());
        Cursor cursor = table.rows();
        for (int i = 0; cursor.advance(); i++) {
            Long nodeId;
            nodeId = Long.parseLong(cursor.column("node_id").toString());
            nodeIdList.add(nodeId);
        }
        return nodeIdList;
    }

    public ToDoList getTodoListById(Long nodeId, Node parent) throws IOException, ParseFailure {
        ToDoList todo = new ToDoList();
        Table table = DatagBaseSingleton.getInstance().getDatabase().execute("SELECT * FROM nodes, todo_lists WHERE nodes.node_id = todo_lists.node_id AND nodes.node_id = " + nodeId.toString());
        Cursor cursor = table.rows();
        for (int i = 0; cursor.advance(); i++) {

            todo.setNodeList(parent);
            todo.setLevel(Integer.parseInt(cursor.column("level").toString()));
            todo.setContent(cursor.column("content").toString());
            todo.setNodeId(Long.parseLong(cursor.column("node_id").toString()));
            todo.setTodoListId(Long.parseLong(cursor.column("todo_list_id").toString()));
            todo.setParent(parent);
            todo.setComplete(Integer.parseInt(cursor.column("is_complete").toString()) == 1);
            Timestamp timestamp = Timestamp.valueOf(cursor.column("date").toString());
            if (timestamp != null) {
                todo.setDate(timestamp.toLocalDateTime());
            }

            return todo;
        }

        return todo;
    }
}
