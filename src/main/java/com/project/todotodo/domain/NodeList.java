package com.project.todotodo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "node_lists")
@Getter
@Setter
public class NodeList {
    // NodeListIterator
    
    @Column(name = "node")
    private Node node;
}
