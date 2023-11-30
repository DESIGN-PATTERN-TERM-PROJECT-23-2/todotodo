package com.project.todotodo.repository;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import com.project.todotodo.model.Node;
import com.project.todotodo.model.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NodeListRepositoryHolub implements NodeListRepositoryInterface {
    @Autowired
    public NodeListRepositoryHolub() {}

    public ArrayList<Node> findCategories(Node root) {
        return null;
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
        return null;
    }

    public ToDoList getTodoListById(Long nodeId, Node parent) {
        return null;
    }

}
