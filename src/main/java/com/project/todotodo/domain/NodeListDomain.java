package com.project.todotodo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "node_lists")
public class NodeListDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_list_id")
    private Long nodeListId;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private NodeDomain parent;

    @OneToMany(mappedBy = "nodeList")
    private List<NodeDomain> nodes = new ArrayList<>();
}
