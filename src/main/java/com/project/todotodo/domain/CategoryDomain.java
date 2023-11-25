package com.project.todotodo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class CategoryDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "node_id")
    private NodeDomain node;
}
