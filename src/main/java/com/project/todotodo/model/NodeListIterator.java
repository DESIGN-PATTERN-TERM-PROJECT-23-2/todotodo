package com.project.todotodo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class NodeListIterator implements Iterator {
    NodeList root;
    Node curr;

    public NodeListIterator(NodeList root){
        this.root = root;
        this.curr = root.getCurr();
    }

    @Override
    public boolean hasNext() {
        if(curr != null){
            if(curr.getNodeList().getChildren().size() > 0){
                return true;
            }
        }
        return false;
    }

    public boolean hasPrevious(){
        if(curr != null){
            if(curr.getNodeList().getParent() != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Node> next() {
        return curr.getNodeList().getChildren();
    }

    public Node previous(){
        return curr.getNodeList().getParent();
    }

    public int findLevel(Node node){
        return node.getLevel();
    }

    public int findPosInLevel(Node node){
        return 0;
    }

    public boolean changePos(Node node, int newLevel, int newPos){
        return false;
    }


    public boolean add(Node node){
        try {
            ArrayList<Node> children = curr.getNodeList().getChildren();
            children.add(node);
            curr.getNodeList().setChildren(children);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    // node 검색 -> 그 node 삭제
    public boolean remove(Node node){
        try {
            ArrayList<Node> children = curr.getNodeList().getChildren();
            children.remove(node);
            curr.getNodeList().setChildren(children);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public int nextIndex(){
        return 0;
    }

    public int previousIndex(){

        return 0;
    }

    public ArrayList<Node> getCategoryList(){
        ArrayList<Node> categoryArrayList;
        categoryArrayList = root.getChildren();
        return categoryArrayList;
    }

    // dfs
    public ArrayList<Node> getAllChildrenWithDFS(Node target){
        ArrayList<Node> allChildren = new ArrayList<Node>();
        ArrayList<Node> needVisit = new ArrayList<Node>();
        needVisit.add(target);
        while(needVisit.size() > 0){
            allChildren.add(curr);
            curr = needVisit.get(needVisit.size()-1);
            needVisit.remove(needVisit.size()-1);
            if(this.hasNext()){
                needVisit.addAll(this.next());
            }
        }
        return allChildren;
    }


   /* public NodeList findNodeInRoot(Node target){

        return null;
    }

    public boolean addToGivenParent(Node parent, Node newNode){
        try {
            NodeList currNodeList = findNodeInRoot(parent);
            currNodeList.getNodeListIterator().add(newNode);
            return true;
        } catch(Exception e){
            return false;
        }
    }*/


}
