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
        // TOBEUPDATED
        return false;
    }

    public boolean hasPrevious(){
        return false;
    }

    public boolean hasChildren(){
        if(curr != null){
            if((curr.getNodeList().getChildren() != null)
                    && (curr.getNodeList().getChildren().size() > 0)){
                return true;
            }
        }
        return false;
    }

    public boolean hasParent(){
        if(curr != null){
            if(curr.getNodeList().getParent() != null){
                return true;
            }
        }
        return false;
    }

    public boolean hasSibling(){
        if(hasParent()){

        }
        return false;
    }

    @Override
    public ArrayList<Node> next() {
        return null;
    }

    public Node previous(){
        return null;
    }

    public ArrayList<Node> getChildren() {
        return curr.getNodeList().getChildren();
    }

    public Node getParent(){
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
        ArrayList<Node> allChildren = new ArrayList<>();
        ArrayList<Node> needVisit = new ArrayList<>();
        needVisit.add(target);
        Node curr;
        while(needVisit.size() > 0){
            curr = needVisit.get(needVisit.size()-1);
            needVisit.remove(needVisit.size()-1);
            allChildren.add(curr);
            if(curr.getNodeList().getChildren() != null){
                ArrayList<Node> children = curr.getNodeList().getChildren();
                for (int i = children.size() - 1; i >= 0; i--) {
                    needVisit.add(children.get(i));
                }
            }
        }
        return allChildren;
    }

    // if returns -1:  error
    public int getIndexAmongChildren(Node target){
        NodeList targetNodeList = target.getNodeList();
        if(targetNodeList.getNodeListIterator().hasPrevious()){
            NodeList parentNodeList = targetNodeList.getParent().getNodeList();
            ArrayList<Node> siblings = parentNodeList.getChildren();
            int index = siblings.indexOf(target);
            return index;
        }
        System.out.println("getIndexAmongChildren():: Error occurred");
        return -1;
    }

   public Node findNodeInRoot(Long targetId){
       ArrayList<Node> needVisit = new ArrayList<Node>();
       needVisit.add(root.getCurr());
       Node curr;
       while(needVisit.size() > 0){
           curr = needVisit.get(needVisit.size()-1);
           needVisit.remove(needVisit.size()-1);
           if(curr.getNodeId() == targetId){
               return curr;
           }
           if(this.hasNext()){
               needVisit.addAll(this.next());
           }
       }
        return null;
    }

    public boolean addToGivenParent(Long parentId, Node newNode){
        try {
            Node currNode = findNodeInRoot(parentId);
            currNode.getNodeList().getNodeListIterator().add(newNode);
            return true;
        } catch(Exception e){
            return false;
        }
    }


}
