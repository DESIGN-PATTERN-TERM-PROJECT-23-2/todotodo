package com.project.todotodo.repository;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.CategoryIndexSingleton;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class NodeListRepositoryHolub {
    Table nodeList;
    Database database;

    public NodeListRepositoryHolub() {
        try {
            insertData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void insertData() throws IOException {
        try {

            Reader in_name = new FileReader("node_lists.csv");
            CSVImporter csvImporter = new CSVImporter(in_name);
            nodeList = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from node_lists");

        } catch (IOException | ParseFailure e) {
            e.printStackTrace();
        }
    }
}
