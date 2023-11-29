package com.project.todotodo.Singleton;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TodoListSingleton {
    private static volatile TodoListSingleton instance;

    private TodoListSingleton() {
    }

    @Getter
    @Setter
    private static Table nodeLists;
    @Getter
    @Setter
    static Database database;
    public static TodoListSingleton getInstance() {
        if (instance == null) {
            synchronized(IndexSingleton.class){
                if (instance == null) {
                    instance = new TodoListSingleton();
                    try {insertData();}
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }

    private static void insertData() throws IOException {
        try {

            Reader in_category = new FileReader("todo_lists.csv");
            CSVImporter csvImporter = new CSVImporter(in_category);
            nodeLists = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from todo_lists");
        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
    }
}

