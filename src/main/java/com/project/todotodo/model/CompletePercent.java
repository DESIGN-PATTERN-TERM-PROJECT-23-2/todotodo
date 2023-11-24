package com.project.todotodo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class CompletePercent extends TodoListDecorator{
    private int percentage;

    private LocalDateTime currentTime;

    public int getPercentage(){
        return 0;
    }

    public LocalDateTime getCurrentTime(){
        return null;
    }
}
