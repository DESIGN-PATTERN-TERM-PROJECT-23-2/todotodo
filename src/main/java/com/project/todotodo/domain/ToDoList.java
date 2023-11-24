package com.project.todotodo.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ToDoList extends Node{
    private Node parent;

    private boolean isComplete;

    private LocalDateTime date;
}
