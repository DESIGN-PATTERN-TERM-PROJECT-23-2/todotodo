package com.project.todotodo.model;

import java.util.ArrayList;
import java.util.Iterator;

public class NodeListIterator implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    public boolean hasPrevious(){
        return false;
    }
    @Override
    public ArrayList<Node> next() {
        return null;
    }

    public Node previous(){
        return null;
    }

    public int findLevel(Node node){
        return 0;
    }

    public int findPosInLevel(Node node){
        return 0;
    }

    public boolean changePos(Node node, int newLevel, int newPos){
        return false;
    }

    public int nextIndex(){
        return 0;
    }

    public int previousIndex(){
        return 0;
    }

    public boolean add(Node node){
        return false;
    }

    public boolean remove(Node node){
        return false;
    }
}
