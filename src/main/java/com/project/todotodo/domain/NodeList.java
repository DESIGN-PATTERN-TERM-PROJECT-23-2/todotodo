package com.project.todotodo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Unsigned;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
public class NodeList {

    NodeListIterator nodeListIterator;

    private Node parent;

    private ArrayList<Node> children;

    public NodeListIterator createIterator(){
        return null;
    }


}
