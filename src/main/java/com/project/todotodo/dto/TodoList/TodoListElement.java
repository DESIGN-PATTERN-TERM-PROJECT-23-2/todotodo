package com.project.todotodo.dto.TodoList;

import com.project.todotodo.model.ToDoList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoListElement {
    private Long NodeId;
    private Long TodoListId;
    private int level;
    private String Content;

    public TodoListElement toDTO(ToDoList node){
        TodoListElement dto = new TodoListElement();
        dto.setNodeId(node.getNodeId());
        dto.setTodoListId(node.getTodoListId());
        dto.setLevel(node.getLevel());
        dto.setContent(node.getContent());
        return dto;
    }
}
