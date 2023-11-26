package com.project.todotodo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class NodeList {

    NodeListIterator nodeListIterator;

    private Node parent;
    private Node curr;

    private ArrayList<Node> children;

    public NodeList(Node curr, Node parent){
        this.curr = curr;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public NodeListIterator createIterator(){
        this.nodeListIterator = new NodeListIterator(this, curr);
        return this.nodeListIterator;
    }


}