package com.project.todotodo.service;

import com.project.todotodo.model.Node;
import com.project.todotodo.model.NodeListIterator;
import com.project.todotodo.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.ListIterator;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final NodeListService nodeListService;
    private NodeListIterator nodeListIterator;



    public TodoListService(TodoListRepository todoListRepository, NodeListService nodeListService) {
        this.todoListRepository = todoListRepository;
        this.nodeListService = nodeListService;
        this.nodeListIterator = nodeListService.getIterator();
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
}
