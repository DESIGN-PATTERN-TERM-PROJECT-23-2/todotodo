package com.project.todotodo.model;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class Node {
    NodeList nodeList;

    private String content;

    private Unsigned level;
}
