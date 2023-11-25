package com.project.todotodo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "node_lists")
public class NodeListDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "node_list_id")
    private Long nodeListId;

    @OneToOne
    @JoinColumn(name = "node_id")
    private NodeDomain parent;


}
