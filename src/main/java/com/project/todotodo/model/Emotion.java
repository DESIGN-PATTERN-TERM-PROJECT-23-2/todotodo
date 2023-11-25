package com.project.todotodo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public abstract class Emotion extends TodoListDecorator {
    private LocalDateTime currentTime;
}
