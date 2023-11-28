package com.project.todotodo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ToDoList extends Node{
    private Node parent;
    private Long todoListId;

    private boolean isComplete;

    private LocalDateTime date;
}
