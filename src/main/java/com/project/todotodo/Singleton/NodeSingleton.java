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

public class NodeSingleton {
    private static volatile NodeSingleton instance;

    private NodeSingleton() {
    }

    @Getter
    @Setter
    private static Table nodes;
    @Getter
    @Setter
    static Database database;
    public static NodeSingleton getInstance() {
        if (instance == null) {
            synchronized(IndexSingleton.class){
                if (instance == null) {
                    instance = new NodeSingleton();
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

            Reader in_category = new FileReader("nodes.csv");
            CSVImporter csvImporter = new CSVImporter(in_category);
            nodes = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from nodes");
        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
    }
}

