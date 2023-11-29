package com.project.todotodo.Singleton;

public class CategoryIndexSingleton {
    private static volatile CategoryIndexSingleton instance;

    private CategoryIndexSingleton() {}
    private Long index;

    public static CategoryIndexSingleton getInstance() {
        if (instance == null) {
            synchronized(CategoryIndexSingleton.instance){
                if (instance == null) {
                    instance = new CategoryIndexSingleton();
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
