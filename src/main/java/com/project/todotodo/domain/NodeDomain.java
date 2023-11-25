package com.project.todotodo.domain;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "nodes")
public class NodeDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_id")
    private Long nodeId;

    @ManyToOne
    @JoinColumn(name = "node_list_id")
    private NodeListDomain nodeList;

    @Column(name = "content")
    private String content;

    @Column(name = "level")
    private Unsigned level;

    @Column(name = "is_category")
    private boolean isCategory;

}
