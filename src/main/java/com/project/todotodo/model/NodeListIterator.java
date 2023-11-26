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
        ArrayList<Node> allList = getAllChildrenWithDFS(root.getCurr());
        int currInx = allList.indexOf(curr);
        int lastInx = allList.size() - 1;
        if(currInx < lastInx){
            return true;
        } else{
            return false;
        }
        /*NodeListIterator currNodeListIterator = curr.getNodeList().getNodeListIterator();
        ArrayList<Node> sibling = currNodeListIterator.getSibling();
        if(sibling.indexOf(curr) < sibling.size() - 1){   // 다음 sibling이 next가 된다
            return true;
        } else{ // 첫째 sibling의 첫 child가 next가 된다.
            NodeListIterator firstSiblingNodeListIterator = sibling.get(0).getNodeList().getNodeListIterator();
            if(firstSiblingNodeListIterator.hasChildren()){
                return true;
            }
        }
        return false;*/
    }

    public boolean hasPrevious(){
        ArrayList<Node> allList = getAllChildrenWithDFS(root.getCurr());
        int currInx = allList.indexOf(curr);
        if(currInx > 0){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public Node next() {
        if(hasNext()){
            ArrayList<Node> allList = getAllChildrenWithDFS(root.getCurr());
            int nextInx = allList.indexOf(curr) + 1;
            curr = allList.get(nextInx);
            return curr;
        }
        return null;
    }

    public Node previous(){
        if(hasPrevious()){
            ArrayList<Node> allList = getAllChildrenWithDFS(root.getCurr());
            int preInx = allList.indexOf(curr) - 1;
            curr = allList.get(preInx);
            return curr;
        }
        return null;
    }

    public Long nextIndex(){
        ArrayList<Node> allList = getAllChildrenWithDFS(root.getCurr());
        return allList.indexOf(curr) + 1L;
    }

    public Long previousIndex(){
        ArrayList<Node> allList = getAllChildrenWithDFS(root.getCurr());
        return allList.indexOf(curr) - 1L;
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
            ArrayList<Node> siblings = getParent().getNodeList().getChildren();
            if(siblings != null && siblings.size() > 0){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Node> getChildren() {
        if(hasChildren()){
            return curr.getNodeList().getChildren();
        }
        return null;
    }

    public Node getParent(){
        if(hasParent()){
            return curr.getNodeList().getParent();
        }
        return null;
    }

    public ArrayList<Node> getSibling(){
        if(hasSibling()){
            return getParent().getNodeList().getChildren();
        }
        return null;
    }

    // curr의 chilrren에 추가
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
    public boolean remove(Long nodeId){
        try {
            Node target = findNodeInRoot(nodeId);
            NodeListIterator targetNodeListIterator = target.getNodeList().getNodeListIterator();
            NodeList parentNodeList = targetNodeListIterator.getParent().getNodeList();
            ArrayList<Node> targetArrayList = targetNodeListIterator.getSibling();
            targetArrayList.remove(target);
            parentNodeList.setChildren(targetArrayList);
            return true;
        } catch(Exception e) {
            return false;
        }
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

    // bfs
    public ArrayList<Node> getAllChildrenWithBFS(Node target){
        ArrayList<Node> allChildren = new ArrayList<>();
        ArrayList<Node> needVisit = new ArrayList<>();
        needVisit.add(target);
        Node curr;
        while(needVisit.size() > 0){
            curr = needVisit.get(0);
            needVisit.remove(0);
            allChildren.add(curr);
            if(curr.getNodeList().getChildren() != null){
                ArrayList<Node> children = curr.getNodeList().getChildren();
                for (int i = 0; i < children.size(); i++) {
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
            if(this.hasChildren()){
                needVisit.addAll(this.getChildren());
            }
        }
        return null;
    }

    public boolean addToGivenParent(Long parentId, Node newNode){
        try {
            Node currNode = findNodeInRoot(parentId);
            ArrayList<Node> children = currNode.getNodeList().getChildren();
            children.add(newNode);
            currNode.getNodeList().setChildren(children);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    /*public int findLevel(Node node){
        return node.getLevel();
    }

    public int findPosInLevel(Node node){
        return 0;
    }

    public boolean changePos(Node node, int newLevel, int newPos){
        return false;
    }
*/
}
