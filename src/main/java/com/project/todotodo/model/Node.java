package com.project.todotodo.model;

import jakarta.persistence.*;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class Node {
    private Long nodeId;
    private NodeList nodeList;
    private String content;

    private int level;

    public void setNodeList(Node parent){
        this.nodeList = new NodeList(this, parent);
    }
}
