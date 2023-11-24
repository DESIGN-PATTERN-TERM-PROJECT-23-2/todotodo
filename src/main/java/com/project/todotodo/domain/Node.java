package com.project.todotodo.domain;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Node {
    // NodeList

    @Column(name = "content")
    private String content;

    @Column(name = "level")
    private Unsigned level;
}
