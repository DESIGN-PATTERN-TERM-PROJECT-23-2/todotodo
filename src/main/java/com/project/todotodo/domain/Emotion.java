package com.project.todotodo.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public abstract class Emotion extends TodoListDecorator {
    private LocalDateTime currentTime;
}
