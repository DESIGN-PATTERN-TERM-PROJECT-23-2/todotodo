package com.project.todotodo.repository;

import com.holub.text.ParseFailure;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface NodeListRepositoryInterface {
    public ArrayList<Node> findCategories(Node root) throws IOException, ParseFailure;
    public ArrayList<Node> findByParentId(Long id, Node parent);
    public List<Long> getNodeIdsByParentId(Long parentId);
    public ToDoList getTodoListById(Long nodeId, Node parent);

}
