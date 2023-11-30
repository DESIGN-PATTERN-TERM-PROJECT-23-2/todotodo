package com.project.todotodo.Singleton;

import com.holub.database.Database;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

public class DatagBaseSingleton {
    private static volatile DatagBaseSingleton instance;

    @Getter
    @Setter
    static Database database;

    private DatagBaseSingleton() {
    }

    public static DatagBaseSingleton getInstance() throws IOException {
        if (instance == null) {
            synchronized(DatagBaseSingleton.class){
                if (instance == null) {
                    instance = new DatagBaseSingleton();
                    database = new Database(new File("."));
                }
            }
        }
        return instance;
    }


}
