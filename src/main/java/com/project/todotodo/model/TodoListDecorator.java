package com.project.todotodo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class TodoListDecorator extends Node{
    protected Node wrappedObj;

    private String description;

    private String fileName;
}
