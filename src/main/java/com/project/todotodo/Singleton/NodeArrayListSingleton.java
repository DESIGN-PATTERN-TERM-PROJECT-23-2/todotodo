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

public class NodeArrayListSingleton {
    private static volatile NodeArrayListSingleton instance;

    private NodeArrayListSingleton() {
    }

    @Getter
    @Setter
    private static Table nodeArrayList;
    @Getter
    @Setter
    static Database database;
    public static NodeArrayListSingleton getInstance() {
        if (instance == null) {
            synchronized(IndexSingleton.class){
                if (instance == null) {
                    instance = new NodeArrayListSingleton();
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

            Reader in_category = new FileReader("node_array_list.csv");
            CSVImporter csvImporter = new CSVImporter(in_category);
            nodeArrayList = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from node_array_list");
        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
    }
}


