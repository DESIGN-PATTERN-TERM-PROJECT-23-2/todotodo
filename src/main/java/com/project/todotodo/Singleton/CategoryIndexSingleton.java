package com.project.todotodo.Singleton;

public class CategoryIndexSingleton {
    private static volatile CategoryIndexSingleton instance;

    private CategoryIndexSingleton() {}

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
}
