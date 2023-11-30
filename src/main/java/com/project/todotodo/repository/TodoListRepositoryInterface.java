package com.project.todotodo.repository;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;

@Repository
public interface TodoListRepositoryInterface {
    public void removeTodolist(Node toDoList);
    public ArrayList<Long> create(Node parent, ToDoList toDoList) throws IOException;

}
