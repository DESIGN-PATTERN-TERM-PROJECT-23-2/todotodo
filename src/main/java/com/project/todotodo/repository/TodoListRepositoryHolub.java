package com.project.todotodo.repository;

import com.holub.database.*;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.*;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public class TodoListRepositoryHolub implements  TodoListRepositoryInterface{
    @Autowired
    public TodoListRepositoryHolub() {}


    @Override
    public void removeTodolist(Long id, Long parentId) {
        // 추후 구현
    }

    public ArrayList<Long> create(Node parent, ToDoList toDoList) throws IOException {
        ArrayList<Long> ids = new ArrayList<>();

        Long nodeListId = IndexSingleton.getInstance().getNodeListIndexAndAddOne();
        NodeListSingleton.getInstance().getNodeLists().insert(new Object[]{String.valueOf(nodeListId), String.valueOf(parent.getNodeId())});
        System.out.println(NodeListSingleton.getNodeLists());
        Writer out = new FileWriter("node_lists.csv");
        NodeListSingleton.getNodeLists().export(new CSVExporter(out));
        out.close();

        Long nodeId = IndexSingleton.getInstance().getNodeIndexAndAddOne();
        NodeSingleton.getInstance().getNodes().insert(new Object[]{String.valueOf(nodeId), toDoList.getContent(), "0", String.valueOf(toDoList.getLevel()), String.valueOf(nodeListId)});
        System.out.println(NodeSingleton.getNodes());
        Writer out2 = new FileWriter("nodes.csv");
        NodeSingleton.getNodes().export(new CSVExporter(out2));
        out2.close();

        Long todoListId = IndexSingleton.getInstance().getTodoListIndexAndAddOne();
        LocalDateTime localDateTime = toDoList.getDate();
        Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());
        TodoListSingleton.getInstance().getTodoLists().insert(new Object[]{String.valueOf(todoListId), String.valueOf(sqlDate), "FALSE", String.valueOf(nodeId), String.valueOf(parent.getNodeId())});
        System.out.println(TodoListSingleton.getTodoLists());
        Writer out3 = new FileWriter("todo_lists.csv");
        TodoListSingleton.getTodoLists().export(new CSVExporter(out3));
        out3.close();

        Long nodeArrayListId = IndexSingleton.getInstance().getNodeArrayListIndexAndAddOne();
        NodeArrayListSingleton.getInstance().getNodeArrayList().insert(new Object[]{String.valueOf(nodeArrayListId), String.valueOf(parent.getNodeId()), String.valueOf(nodeListId) });
        Writer out4 = new FileWriter("node_array_list.csv");
        NodeArrayListSingleton.getNodeArrayList().export(new CSVExporter(out4));
        out4.close();

        ids.add(nodeId);
        ids.add(todoListId);
        return ids;
    }

}
