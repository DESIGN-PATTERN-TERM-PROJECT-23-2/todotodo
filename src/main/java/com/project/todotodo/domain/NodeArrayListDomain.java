package com.project.todotodo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "node_array_list")
public class NodeArrayListDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_array_list_id")
    private Long nodeArrayListId;

    @OneToOne
    @JoinColumn(name = "node_id")
    private NodeDomain node;

    @OneToOne
    @JoinColumn(name = "node_list_id")
    private NodeListDomain nodeList;
}
