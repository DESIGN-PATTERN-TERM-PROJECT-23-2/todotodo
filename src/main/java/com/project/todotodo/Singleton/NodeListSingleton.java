package com.project.todotodo.Singleton;

public class NodeListSingleton {
    private static volatile NodeListSingleton instance;

    private NodeListSingleton() {}
    private Long index;

    public static NodeListSingleton getInstance() {
        if (instance == null) {
            synchronized(NodeListSingleton.instance){
                if (instance == null) {
                    instance = new NodeListSingleton();
                }
            }
        }
        return instance;
    }

    public Long getIndex() {
        return index;
    }

    public Long getIndexAndAddOne(){
        return index++;
    }

    public void setIndex(Long idx){
        this.index = idx;
        return;
    }
}