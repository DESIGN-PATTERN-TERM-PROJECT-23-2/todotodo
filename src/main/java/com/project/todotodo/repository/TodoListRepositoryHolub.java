package com.project.todotodo.repository;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.*;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public class TodoListRepositoryHolub {
    @Autowired
    public TodoListRepositoryHolub() {}

    public void removeTodolist(Node toDoList){

    }

    public ArrayList<Long> create(Node parent, ToDoList toDoList){
        ArrayList<Long> ids = new ArrayList<>();

        Long nodeListId = IndexSingleton.getInstance().getNodeListIndexAndAddOne();
        NodeListSingleton.getInstance().getNodeLists().insert(new Object[]{String.valueOf(nodeListId), String.valueOf(parent.getNodeId())});
        System.out.println(NodeListSingleton.getNodeLists());

        Long nodeId = IndexSingleton.getInstance().getNodeIndexAndAddOne();
        NodeSingleton.getInstance().getNodes().insert(new Object[]{String.valueOf(nodeId), toDoList.getContent(), "0", String.valueOf(toDoList.getLevel()), String.valueOf(nodeListId)});
        System.out.println(NodeSingleton.getNodes());

        Long todoListId = IndexSingleton.getInstance().getTodoListIndexAndAddOne();
        LocalDateTime localDateTime = toDoList.getDate();
        Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());
        TodoListSingleton.getInstance().getTodoLists().insert(new Object[]{String.valueOf(todoListId), String.valueOf(sqlDate), "FALSE", String.valueOf(nodeId), String.valueOf(parent.getNodeId())});
        System.out.println(TodoListSingleton.getTodoLists());

        Long nodeArrayListId = IndexSingleton.getInstance().getNodeArrayListIndexAndAddOne();
        NodeArrayListSingleton.getInstance().getNodeArrayList().insert(new Object[]{String.valueOf(nodeArrayListId), String.valueOf(parent.getNodeId()), String.valueOf(nodeListId) })

        ids.add(nodeId);
        ids.add(todoListId);
        return ids;
    }

}
