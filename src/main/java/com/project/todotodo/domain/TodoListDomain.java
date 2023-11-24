package com.project.todotodo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "todo_lists")
public class TodoListDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_list_id")
    private Long todoListId;

    @OneToOne
    @JoinColumn(name = "node_id")
    private NodeDomain parent;

    @Column(name = "is_complete")
    private int isComplete;

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;
}
