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

    public NodeListIterator createIterator(){
        return null;
    }


}