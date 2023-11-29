package com.project.todotodo.Singleton;

public class NodeListSingleton {
    private static volatile NodeListSingleton instance;

    private NodeListSingleton() {}

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
}