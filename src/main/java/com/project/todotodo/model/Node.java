package com.project.todotodo.model;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class Node {
    private int nodeId;
    NodeList nodeList;

    private String content;

    private int level;
}
