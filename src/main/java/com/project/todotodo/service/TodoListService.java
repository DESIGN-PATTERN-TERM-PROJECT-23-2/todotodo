package com.project.todotodo.service;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeList;
import com.project.todotodo.model.NodeListIterator;
import com.project.todotodo.model.ToDoList;
import com.project.todotodo.repository.TodoListRepository;
import com.project.todotodo.repository.TodoListRepositoryClass;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // create할 때 잊지 말고 parent id로 가서 list 레포 행동하
    public void deleteChildrenTodoListById(Long id) {
        ArrayList<Node> children = nodeListIterator.getAllChildrenWithBFS(nodeListIterator.findNodeInRoot(id));
        ListIterator<Node> iterator = children.listIterator(children.size());

        while (iterator.hasPrevious()) {
            Node currentNode = iterator.previous();
            deleteSingleTodoListById(currentNode.getNodeId());
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
        todo.setParent(parent);
        todo.setComplete(false);
        todo.setDate(time);
        todo.setLevel(parent.getLevel()+1);
        todo.setNodeList(new NodeList());

        Long node_id = todoListRepositoryClass.create(parent, todo);
        todo.setNodeId(node_id);

        nodeListIterator.addToGivenParent(parent_id, todo);

        return node_id;
    }
}
