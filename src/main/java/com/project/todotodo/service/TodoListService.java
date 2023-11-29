package com.project.todotodo.service;

import com.project.todotodo.dto.Goal.CategoryListElement;
import com.project.todotodo.dto.TodoList.TodoListElement;
import com.project.todotodo.model.*;
import com.project.todotodo.repository.TodoListRepository;
import com.project.todotodo.repository.TodoListRepositoryClass;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final NodeListService nodeListService;
    private NodeListIterator nodeListIterator;

    private final TodoListRepositoryClass todoListRepositoryClass;



    public TodoListService(TodoListRepository todoListRepository, NodeListService nodeListService, TodoListRepositoryClass todoListRepositoryClass) {
        this.todoListRepository = todoListRepository;
        this.nodeListService = nodeListService;
        this.nodeListIterator = nodeListService.getIterator();
        this.todoListRepositoryClass = todoListRepositoryClass;
    }

    public void deleteChildrenTodoListById(Long id) {
        Node target = nodeListIterator.findNodeInRoot(id);
        ArrayList<Node> children = nodeListIterator.getAllChildrenWithBFS(target);
        if(children == null){
            System.out.println("TodoListService.java : deleteChildrenTodoListById() ::no children of given id");
            return;
        }

        ListIterator<Node> iterator = children.listIterator(children.size());
        while (iterator.hasPrevious()) {
            Node currentNode = iterator.previous();
            //deleteSingleTodoListById(currentNode.getNodeId());
            deleteTodoListById(currentNode.getNodeId());
        }
        return;
    }

    public void deleteSingleTodoListById(Long id){
        nodeListIterator.remove(id);
        todoListRepository.deleteById(id);
        return;
    }

    public void deleteTodoListById(Long id){
        deleteChildrenTodoListById(id);
        deleteSingleTodoListById(id);
        return;
    }

    public Long createTodoList(Long parent_id, String content, LocalDateTime time){
        ToDoList todo = new ToDoList();
        Node parent = nodeListIterator.findNodeInRoot(parent_id);
        if(parent == null){
            System.out.println("TodoListService.java: createTodoList():: no such parent");
            return 0L;
        }
        todo.setParent(parent);
        todo.setComplete(false);
        todo.setDate(time);
        todo.setLevel(parent.getLevel()+1);
        todo.setNodeList(parent);

        /*
        Long node_id = todoListRepositoryClass.create(parent, todo);
        todo.setNodeId(node_id);
        */

        // node_id, todo_list_id
        ArrayList<Long> ids = todoListRepositoryClass.create(parent, todo);
        Long node_id = ids.get(0);
        Long todo_list_id = ids.get(1);
        todo.setNodeId(node_id);
        todo.setTodoListId(todo_list_id);

        nodeListIterator.addToGivenParent(parent_id, todo);

        return node_id;
    }




}
