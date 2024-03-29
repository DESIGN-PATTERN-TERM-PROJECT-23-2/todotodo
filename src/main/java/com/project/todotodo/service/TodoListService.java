package com.project.todotodo.service;

import com.project.todotodo.model.*;
import com.project.todotodo.repository.TodoListRepository;
import com.project.todotodo.repository.TodoListRepositoryHolub;
import com.project.todotodo.repository.TodoListRepositoryInterface;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ListIterator;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final NodeListService nodeListService;
    private NodeListIterator nodeListIterator;

    private final TodoListRepositoryInterface todoListRepositoryClass;

    private final TodoListRepositoryHolub todoListRepositoryHolub;



    public TodoListService(TodoListRepository todoListRepository, NodeListService nodeListService, TodoListRepositoryInterface todoListRepositoryClass, TodoListRepositoryHolub todoListRepositoryHolub) {
        this.todoListRepository = todoListRepository;
        this.nodeListService = nodeListService;
        this.nodeListIterator = nodeListService.getIterator();
        this.todoListRepositoryClass = todoListRepositoryClass;
        this.todoListRepositoryHolub = todoListRepositoryHolub;
    }

    public void deleteChildrenTodoListById(Long id)
    {
        Node target = nodeListIterator.findNodeInRoot(id);
        ArrayList<Node> children = nodeListIterator.getAllChildrenWithBFS(target);
        if(children == null){
            System.out.println("TodoListService.java : deleteChildrenTodoListById() ::no children of given id");
            return;
        }

        ListIterator<Node> iterator = children.listIterator(children.size());
        while (iterator.hasPrevious()) {
            Node currentNode = iterator.previous();
            Long parentId = currentNode.getNodeList().getParent().getNodeId();
            deleteSingleTodoListById(currentNode.getNodeId(), parentId);
            //deleteTodoListById(currentNode.getNodeId());
        }
        return;
    }
    public void deleteSingleTodoListById(Long id, Long parentId){
        todoListRepositoryClass.removeTodolist(id, parentId);
        nodeListIterator.remove(id);
        return;
    }

    public void deleteTodoListById(Long id, Long parentId){
        deleteChildrenTodoListById(id);
        deleteSingleTodoListById(id, parentId);
        return;
    }

    public Long createTodoList(Long parent_id, String content, LocalDateTime time) throws IOException {
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
        todo.setContent(content);

        /*
        Long node_id = todoListRepositoryClass.create(parent, todo);
        todo.setNodeId(node_id);
        */

        // node_id, todo_list_id
        ArrayList<Long> ids = todoListRepositoryHolub.create(parent, todo);
        if(ids == null || ids.size() != 2){
            System.out.println("failed to save in todoList DB");
            return 0L;
        }
        Long node_id = ids.get(0);
        Long todo_list_id = ids.get(1);
        todo.setNodeId(node_id);
        todo.setTodoListId(todo_list_id);

        nodeListIterator.addToGivenParent(parent_id, todo);

        return node_id;
    }




}
