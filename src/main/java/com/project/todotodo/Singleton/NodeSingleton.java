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

        } catch (IOException  e){
            e.printStackTrace();
        }
    }
}

