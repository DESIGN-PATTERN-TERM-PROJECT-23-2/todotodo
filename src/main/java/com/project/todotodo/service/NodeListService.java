package com.project.todotodo.service;

import com.holub.text.ParseFailure;
import com.project.todotodo.dto.TodoList.CategoryList;
import com.project.todotodo.dto.TodoList.TodoListElement;
import com.project.todotodo.model.*;
import com.project.todotodo.repository.NodeArrayListRepositoryHolub;
import com.project.todotodo.repository.NodeListRepository;
import com.project.todotodo.repository.NodeListRepositoryHolub;
import com.project.todotodo.repository.NodeListRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NodeListService {
    private final NodeListRepository nodeListRepository;
    private NodeList nodeList;
    private NodeListIterator nodeListIterator;

    private final NodeListRepositoryInterface nodeListRepositoryClass;

    private final NodeListRepositoryHolub nodeListRepositoryHolub;

    public NodeListService(NodeListRepository nodeListRepository, NodeListRepositoryInterface nodeListRepositoryClass, NodeListRepositoryHolub nodeListRepositoryHolub) throws IOException, ParseFailure {

        this.nodeListRepository = nodeListRepository;
        this.nodeListRepositoryClass = nodeListRepositoryClass;
        this.nodeListRepositoryHolub = nodeListRepositoryHolub;
        this.nodeList = initNodeList();
        System.out.println("created NodeListService-------");
    }

    private NodeList initNodeList() throws IOException, ParseFailure {
        Node root = new Root();
        // NodeList nodeList = new NodeList(root, null);
        this.nodeList = new NodeList(root, null);
        this.nodeListIterator = this.nodeList.createIterator();
        for (Node element : nodeListRepositoryHolub.findCategories(root)) {
            nodeListIterator.add(element);
            System.out.println("added category -----");
        }

        while(nodeListIterator.hasNext()){
            // ArrayList<Node>
            Node parent = nodeListIterator.next();
            Node savedCurr = nodeListIterator.getCurr();
            nodeListIterator.setCurr(parent);
            ArrayList<Node> children = nodeListRepositoryHolub.findByParentId(parent.getNodeId(), parent);
            if(children != null) {
                for (Node element : children) {
                    nodeListIterator.add(element);
                }
            }
            nodeListIterator.setCurr(savedCurr);
        }
        nodeListIterator.initCurr();
        // 이제 iterator로 순회하기...
        return nodeList;
    }

    public NodeListIterator getIterator(){
        return this.nodeListIterator;
    }

    public List<CategoryList> getCategoryListByDate(LocalDate date){
        List<CategoryList> categoryLists = new ArrayList<>();

        ArrayList<Node> categories = nodeListIterator.getCategoryList();
        for(Node node: categories){
            Category category = (Category) node;
            CategoryList categoryList = new CategoryList();

            Long nodeId = category.getNodeId();
            categoryList.setNodeId(nodeId);
            categoryList.setCategoryId(category.getCategoryId());
            categoryList.setContent(category.getContent());
            List<TodoListElement> todoListElementList = getAllTodoListsOfCategoryByDate(nodeId, date);
            categoryList.setTodoListElementList(todoListElementList);

            categoryLists.add(categoryList);
        }
        // to be updated
        return categoryLists;
    }

    public List<TodoListElement> getAllTodoListsOfCategoryByDate(Long nodeIdOfCategory, LocalDate date){
        List<ToDoList> todolists = nodeListIterator.getTodoListByCategoryDate(nodeIdOfCategory, date);
        List<TodoListElement> todolistDtoList = new ArrayList<>();
        for (ToDoList todolist : todolists) {
            TodoListElement todolistDto = new TodoListElement().toDTO(todolist);
            System.out.println(todolist.getContent());
            todolistDtoList.add(todolistDto);
        }
        return todolistDtoList;
    }

}
