package com.project.todotodo.Singleton;

public class TodoListSingleton {
    private static volatile TodoListSingleton instance;

    private TodoListSingleton() {}
    private Long index;

    public static TodoListSingleton getInstance() {
        if (instance == null) {
            synchronized(TodoListSingleton.instance){
                if (instance == null) {
                    instance = new TodoListSingleton();
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

